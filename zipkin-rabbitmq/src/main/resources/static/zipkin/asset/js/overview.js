/**
 * Created by weichunhe on 2016/8/29.
 */
require("app").register.controller("OverviewController", function ($scope, $timeout, $myhttp, $location) {

    var searchParam = $location.search();
    $scope.startDate = new Date(searchParam.startDate - 0 || (new Date().getTime() - 7 * 24 * 3600000));
    $scope.endDate = searchParam.endDate ? new Date(searchParam.endDate - 0) : new Date();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init($('#main')[0]);
    var services = [], denpendencies = [];
    var moment = require('moment');

    $scope.analyse = function () {
        $scope.loading = true;
        var param = {};
        param.startTime = moment($scope.startDate).format('x');
        param.endTime = moment($scope.endDate).format('x');
        $.when($myhttp.get('api/v1/services', null, function (data) {
            services = data;
        }), $myhttp.get('api/v1/dependencies', {
            endTs: param.endTime,
            lookback: param.endTime - param.startTime
        }, function (data) {
            denpendencies = data;
        })).then(function () {
            $location.search('startDate', param.startTime);
            $location.search('endDate', param.endTime);
            $scope.loading = false;
            makeChart(denpendencies, services);
        });
    };

    /**
     * 服务类别
     * @param services
     * @returns {Array}
     */
    function getCategories(services) {
        var categories = [];
        for (var i = 0; i < services.length; i++) {
            categories[i] = {
                name: services[i]
            };
        }
        return categories;
    }

    function makeChart(denpendencies, services) {
        var links = [], nodes = [], categories = getCategories(services);
        var callCounter = {}, sendCounter = {};
        _.each(denpendencies, function (item, i) {

            if (item.parent === item.child) {
                return;//排除自身调用
            }
            callCounter[item.child] = (callCounter[item.child] || 0) + item.callCount;
            sendCounter[item.parent] = (callCounter[item.parent] || 0) + item.callCount;
            var link = {
                source: item.parent,
                target: item.child,
                data: {
                    callTotal: item.callCount
                },
                lineStyle: {
                    normal: {
                        width: 2
                    }
                }
            };
            link.id = link.name = item.parent + "->" + item.child;
            links.push(link);
        });

        _.each(categories, function (c, i) {
            var count = callCounter[c.name] || 0;
            nodes.push({
                id: c.name,
                name: c.name,
                category: c.name,
                value: i,
                symbolSize: count,
                label: {
                    normal: {
                        show: false //node.symbolSize > 10
                    }
                },
                draggable: true,
                data: {
                    callTotal: count,
                    sendTotal: sendCounter[c.name] || 0
                }
            })
            ;
        });
        // 对符号大小进行计算
        var maxNode = _.maxBy(nodes, function (n) {
            return n.symbolSize;
        });
        var maxSize = Math.max(maxNode.symbolSize, 5);
        _.each(nodes, function (n) {
            n.symbolSize = calcValWithMaxAndMin(15, 50, n.symbolSize, maxSize);
        });
        // //对连接线大小进行计算
        // var maxLink = _.maxBy(links, function (l) {
        //     return l.data.callTotal;
        // });
        // var maxWidth = Math.max(maxLink.data.callTotal, 5);
        // _.each(links, function (n) {
        //     n.lineStyle.normal.width = calcValWithMaxAndMin(1, 5, n.data.callTotal, maxWidth);
        // });

        // 指定图表的配置项和数据
        var option = {
            // title: {
            //     text: 'Les Miserables',
            //     subtext: 'Circular layout',
            //     top: 'bottom',
            //     left: 'right'
            // },
            tooltip: {
                enterable: true,
                formatter: function (params, ticket, callback) {
                    var html = [];
                    html.push('<i class="fa  fa-circle" style="color:' + params.color + '"></i>&nbsp;');
                    html.push(params.name + '<br/>');
                    if (params.dataType === 'node') {
                        var startTime = moment($scope.startDate).format('x');
                        var endTime = moment($scope.endDate).format('x');
                        html.push('请求了' + params.data.data.sendTotal + '次<br/>');
                        html.push('被请求了' + params.data.data.callTotal + '次<br/>');
                        html.push('<a href="#/serviceDetail?serviceId=' + params.name + '&startDate=' + startTime + '&endDate=' + endTime + '">查看详情</a>');
                    } else if (params.dataType === 'edge') {
                        html.push('请求了' + params.data.data.callTotal + '次<br/>');
                    }
                    console.log(params);
                    return html.join('');
                }
            },
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            series: [
                {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',//'circular',
                    edgeSymbol: ['none', 'arrow'],
                    force: {
                        repulsion: [2000, 2500]
                    },
                    // circular: {
                    //     rotateLabel: true
                    // },
                    data: nodes,
                    links: links,
                    categories: categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.4
                        }
                    }
                }
            ]
        };

// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

    /**
     * 将给定val对应到变化范围内
     * @param base 基础值
     * @param range 变化范围
     * @param val 当前值
     * @param max 最大值
     */
    function calcValWithMaxAndMin(base, range, val, max) {
        return base + val / max * range;
    }

    $scope.analyse();
});