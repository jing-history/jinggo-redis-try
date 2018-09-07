/**
 * Created by gz12 on 2018-09-07.
 */
Vue.component('tabs',{
    props: {
      name: {
          type: String
      },
        label: {
          type: String,
            default: ''
        }
    },
    template: '<div class="tabs">' +
    '<div class="tabs-bar">' +
    '' +
    '</div>' +
    '<div class="tabs-content">' +
    '<slot></slot>' +
    '</div>' +
    '</div>',
    data: function () {
        return {
            //用于渲染tabs 的标题
            navList: []
        }
    },
    methods: {
        getTabs(){
            //通过遍历子组件，得到所有的pane 组件
        }
    }
});