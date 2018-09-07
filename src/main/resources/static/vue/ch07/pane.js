/**
 * Created by gz12 on 2018-09-07.
 */
Vue.component('pane',{
    name: 'pane',
    template: '<div class="pane" v-show="show">' +
    '<slot></slot>' +
    '</div>',
    data:function () {
        return {
            show: true
        }
    },
    methods: {
        updateNav() {
         this.$parent.updateNav();
        }
    },
    watch: {
        label(){
            this.updateNav();
        }
    },
    mounted(){
        this.updateNav();
    }
});