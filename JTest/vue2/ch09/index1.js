/**
 * Created by gz12 on 2018-09-10.
 */
var app = new Vue({
    el: '#app',
    data: {
        columns: [
            {
                title: '姓名',
                key: 'name'
            },
            {
                title: '年龄',
                key: 'age',
                sortable: true
            },
            {
                title: '出生日期',
                key: 'birthday',
                sortable: true
            },
            {
                title: '地址',
                key: 'address'
            }
        ],
        data: [
            {
                name: '王小明',
                age: 18,
                birthday: '1999-02-21',
                address: '萨克达就是端口'
            },
            {
                name: '二狗',
                age: 20,
                birthday: '1999-02-21',
                address: '萨克达就是端口'
            },
            {
                name: '三狗',
                age: 18,
                birthday: '1999-11-21',
                address: '萨克达就是端口'
            },
            {
                name: '大胖',
                age: 33,
                birthday: '1986-02-21',
                address: '萨克达就是端口'
            }
        ]
    },
    methods: {
        handleAddData: function () {
            this.data.push({
                name: '刘小天',
                age: 20,
                birthday: '1999-05-06',
                address: '北京啊山东科技'
            });
        }
    }
});