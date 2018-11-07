<style lang="less" scoped>
    @import "./leanManage.less";
</style>
<template>
    <div id="app">
        <h1>{{title}}</h1>
        <ul class="todos">
            <li v-for="(todo,index) in todos" :class="{'checked':todo.done}">
                <label>{{index + 1}}.{{todo.value}}</label>
                <time>{{todo.created}}</time>
            </li>
        </ul>
    </div>

</template>
<script>
    import {
        getTimeListData,
        addTime,
        editTime
    } from "@/api/index";
    export default {
        name: "lean-manage",
        data() {
          return {
            title: "vue-todos",
            todos: [
                {value: "my vue one", done: false, created: Date.now()},
                {value: "my vue two", done: true, created: Date.now() + 30000},
                {value: "my vue three", done: false,  created: Date.now() - 30000}
            ],
          };
        },
        methods: {
        },
        mounted() {
        }
    };
</script>