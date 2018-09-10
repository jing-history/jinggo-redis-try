/**
 * Created by gz12 on 2018-09-10.
 */
var pp = new Vue({
    el: '#app',
    data: {
        username: '',
        message: '',
        list: []
    },
    methods: {
        handleSend: function () {
            if(this.username === ''){
                window.alert('请输入昵称');
                return;
            }
            if(this.message === ''){
                window.alert('请输入内容');
                return;
            }
            this.list.push({
                name: this.username,
                message: this.message
            });
            this.message = '';
        },
        hnadleReply: function (index) {
            var name = this.list[index].name;
            this.message = '回复@' + name + '：';
            this.$refs.message.focus();
        }
    }
});