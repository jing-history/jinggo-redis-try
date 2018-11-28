/**
 * Created by gz12 on 2018-09-07.
 */
var app = new Vue({
   el: '#app',
    data: {
       show: false
    },
    methods:{
        handleClose: function () {
            this.show = false;
        }
    }
});