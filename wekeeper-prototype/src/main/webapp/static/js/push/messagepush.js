/**
 * Created by shangyindong on 2016/6/17.
 */
var _mp_ws = null;
var _mp_ajax_it = null;

function wsMsgPush(url, params,onmessage,onclose,onerror){
    _mp_ws = new WebSocket("ws://"+url);
    if(!_mp_ws){ return; }

    _mp_ws.onopen = function(){
        _mp_ws.send(params);
    };
    if(onmessage) _mp_ws.onmessage = function(evt){ onmessage(evt.data); }
    if(onerror) _mp_ws.onerror = function (evt){ onerror(); }
    if(onclose) _mp_ws.onclose = function (evt){ onclose(); }
}

function wsChatMsg(msg){
    _mp_ws.send(msg);
}
function ajaxMsgPush(url, params,interval,onmessage,onclose,onerror){
    function __getmsg(){
        $.ajax({
            url:				url,
            data:			params,
            cache:			true,
            type:			"get",
            dataType:		"text",
            success:		function(data, textStatus, jqXHR){
                if(onmessage) onmessage(data);
            },
            error:			function(jqXHR, textStatus, errorThrown){
                if(onerror) onerror();
            },
            complete:		function(jqXHR, textStatus){
                if(onclose) onclose();
            }
        });
    }

    _mp_ajax_it = setInterval("__getmsg()",interval);
}

function stopMsgPush(){
    if(_mp_ws){
        _mp_ws.send("disconnect");
        _mp_ws.close();
    }

    if(_mp_ajax_it){
        clearInterval(_mp_ajax_it);
    }
}

var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
function generateMixed(n) {
    var res = "";
    for(var i = 0; i < n ; i ++) {
        var id = Math.ceil(Math.random()*35);
        res += chars[id];
    }
    return res;
}