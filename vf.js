var map = {}, hits = payload.hits.hits;
for (var i = 0; i < hits.length; i++) {
    var source = hits[i]._source;
    if (!map[source.partner_id]) {
        map[source.partner_id] = {};
    }
    var partner = map[source.partner_id];
    if (!partner[source.interface_type]) {
        partner[source.interface_type] = [];
    }
    var request = {};
    request[source.request_time] = source.consuming_time;
    partner[source.interface_type].push(request);
}
var result = [], partner_index = 0, request_index = 0;
for (var id in map) {
    var partner = map[id];
    var infs = [];
    result.push({index: ++partner_index, id: id, name: '商户号', infs: infs});
    var inf_index = 0;
    for (var name in partner) {
        var requests = [];
        infs.push({index: ++inf_index, id: name, name: '接口名', requests: requests});
        var inf = partner[name];
        for (var i = 0; i < inf.length; i++) {
            for (var k in inf[i]) {
                var v = inf[i][k];
                requests.push({index: ++request_index, request_time: k, consume_time: v});
            }
        }
    }
}
payload.result = result;