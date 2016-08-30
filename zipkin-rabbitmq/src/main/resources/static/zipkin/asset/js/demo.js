require("app").register.controller("DemoController", function ($scope, $timeout, $myhttp) {
    var investStatuses = $scope.investStatuses = [{
        text: '全部', value: 'REFUNDING,BID_FAIL,INIT,PAY_SUCCESS,BID_SUCCESS,REFUND_SUCCESS'
    }, {
        text: '待支付', value: 'INIT'
    }, {
        text: '支付成功', value: 'PAY_SUCCESS'
    }, {
        text: '确认成功', value: 'BID_SUCCESS'
    }, {
        text: '已退款', value: 'REFUND_SUCCESS'
    }];
    $scope.orderStatus = investStatuses[0].value; //默认选择全部
    $scope.startDate = new Date(new Date().getTime() - 30 * 24 * 3600000);
    $scope.endDate = new Date();

    // var moment = require('moment');
    // param.startTime = moment($scope.startDate).format('x');
    // param.endTime = moment($scope.endDate).format('x');

});