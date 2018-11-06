<style lang="less">
</style>
<template>
    <div id="app">
        <h1>{{title}}</h1>
        <ul>
            <li v-for="(todo,index) in todos" :id="index">
                <label>{{ index + 1 }}.{{ todo.value }}</label>
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
                {value: "my vue one", done: false},
                {value: "my vue two", done: false},
                {value: "my vue three", done: false}
            ],
          };
        },
        methods: {
        },
        mounted() {
        }
    };
</script>