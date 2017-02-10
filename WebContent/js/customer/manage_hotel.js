/**
 * Created by xu on 2017/2/10.
 */

function pass(hid) {
    var state = document.getElementById('form'.hid).firstElementChild;
    state.value='1';
}

function unpass(hid) {
    var state = document.getElementById('form'.hid).firstElementChild;
    state.value='0';
}