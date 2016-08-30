/**
 * Created by weichunhe on 2016/8/29.
 */
require("app").register.controller("OverviewController", function ($scope, $timeout, $myhttp) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init($('#main')[0]);
    var services = [], denpendencies = [];
    $.when($myhttp.get('api/v1/services', null, function (data) {
        services = data;
    }), $myhttp.get('api/v1/dependencies', {endTs: new Date().getTime()}, function (data) {
        denpendencies = data;
    })).then(function () {
        makeChart(denpendencies, services);
    });

    function getCategories(services) {
        var categories = [];
        for (var i = 0; i < services.length; i++) {
            categories[i] = {
                name: services[i],
                count: 0
            };
        }
        return categories;
    }

    function makeChart(denpendencies, services) {
        var links = [], nodes = [], categories = getCategories(services);
        var callCounter = {};
        _.each(denpendencies, function (item, i) {

            if (item.parent === item.child) {
                return;//排除自身调用
            }
            callCounter[item.child] = (callCounter[item.child] || 0) + item.callCount;
            var link = {
                id: 'link-' + i,
                name: item.parent + "->" + item.child,
                source: item.parent,
                target: item.child
            };
            links.push(link);

        });
        _.each(categories, function (c, i) {
            var count = callCounter[c.name] || 0;
            nodes.push({
                id: c.name,
                name: c.name,
                category: c.name,
                value: i,
                symbolSize: Math.max(count * 10, 10),
                label: {
                    normal: {
                        show: true //node.symbolSize > 10
                    }
                },
                draggable: true
            });
        });

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'Les Miserables',
                subtext: 'Circular layout',
                top: 'bottom',
                left: 'right'
            },
            tooltip: {},
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
                    force: {
                        repulsion: [300,800]
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
                            curveness: 0.3
                        }
                    }
                }
            ]
        };

// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

    // var graph = {};
    // graph.nodes = [{
    //     "id": "0",
    //     "name": "Myriel",
    //     "itemStyle": {"normal": {"color": "rgb(235,81,72)"}},
    //     "symbolSize": 28.685715,
    //     "attributes": {"modularity_class": 0}
    // }, {
    //     "id": "1",
    //     "name": "Napoleon",
    //     "itemStyle": {"normal": {"color": "rgb(236,81,72)"}},
    //     "symbolSize": 12,
    //     "attributes": {"modularity_class": 0}
    // }, {
    //     "id": "2",
    //     "name": "MlleBaptistine",
    //     "itemStyle": {"normal": {"color": "rgb(236,81,72)"}},
    //     "symbolSize": 9.485714,
    //     "attributes": {"modularity_class": 1}
    // }, {
    //     "id": "3",
    //     "name": "MlleBaptistine",
    //     "itemStyle": {"normal": {"color": "rgb(236,81,72)"}},
    //     "symbolSize": 9.485714,
    //     "attributes": {"modularity_class": 1}
    // }];
    // graph.links = [{"id": "0", "name": null, "source": "1", "target": "0", "lineStyle": {"normal": {}}}, {
    //     "id": "1",
    //     "name": null,
    //     "source": "2",
    //     "target": "0",
    //     "lineStyle": {"normal": {}}
    // }, {"id": "2", "name": null, "source": "1", "target": "2", "lineStyle": {"normal": {width: 13, curveness: 0.4}}}, {
    //     "id": "3",
    //     "name": null,
    //     "source": "3",
    //     "target": "3",
    //     "lineStyle": {"normal": {"width": 5, curveness: 0.1}}
    // }];

    // graph.nodes.forEach(function (node) {
    //     node.itemStyle = null;
    //     node.value = node.symbolSize;
    //     node.symbolSize /= 1.5;
    //     node.label = {
    //         normal: {
    //             show: node.symbolSize > 10
    //         }
    //     };
    //     node.category = node.attributes.modularity_class;
    // });


});