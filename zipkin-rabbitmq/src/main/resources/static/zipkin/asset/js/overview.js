/**
 * Created by weichunhe on 2016/8/29.
 */
require("app").register.controller("OverviewController", function ($scope, $timeout, $myhttp) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init($('#main')[0]);

    var graph = {};
    graph.nodes = [{
        "id": "0",
        "name": "Myriel",
        "itemStyle": {"normal": {"color": "rgb(235,81,72)"}},
        "symbolSize": 28.685715,
        "attributes": {"modularity_class": 0}
    }, {
        "id": "1",
        "name": "Napoleon",
        "itemStyle": {"normal": {"color": "rgb(236,81,72)"}},
        "symbolSize": 12,
        "attributes": {"modularity_class": 0}
    }, {
        "id": "2",
        "name": "MlleBaptistine",
        "itemStyle": {"normal": {"color": "rgb(236,81,72)"}},
        "symbolSize": 9.485714,
        "attributes": {"modularity_class": 1}
    }, {
        "id": "3",
        "name": "MlleBaptistine",
        "itemStyle": {"normal": {"color": "rgb(236,81,72)"}},
        "symbolSize": 9.485714,
        "attributes": {"modularity_class": 1}
    }];
    graph.links = [{"id": "0", "name": null, "source": "1", "target": "0", "lineStyle": {"normal": {}}}, {
        "id": "1",
        "name": null,
        "source": "2",
        "target": "0",
        "lineStyle": {"normal": {}}
    }, {"id": "2", "name": null, "source": "1", "target": "2", "lineStyle": {"normal": {width: 13, curveness: 0.4}}}, {
        "id": "3",
        "name": null,
        "source": "3",
        "target": "3",
        "lineStyle": {"normal": {"width": 5, curveness: 0.1}}
    }];

    graph.nodes.forEach(function (node) {
        node.itemStyle = null;
        node.value = node.symbolSize;
        node.symbolSize /= 1.5;
        node.label = {
            normal: {
                show: node.symbolSize > 10
            }
        };
        node.category = node.attributes.modularity_class;
    });
    var categories = [];
    for (var i = 0; i < 9; i++) {
        categories[i] = {
            name: '类目' + i
        };
    }
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
                layout: 'circular',
                circular: {
                    rotateLabel: true
                },
                data: graph.nodes,
                links: graph.links,
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
})