var app = new Vue({
    el: '#app',
    data: {
      list: [
          {
            id: 1,
            name: 'iphone 7',
            price: 6188,
            count:1
          },
          {
              id: 1,
              name: 'ipad pro',
              price: 5888,
              count:1
          },
          {
              id: 1,
              name: 'macbook pro',
              price: 9888,
              count:1
          }
      ]
    },
    computed: {
        totalPrice: function () {
            var total = 0;
            for (var i = 0; i < this.list.length; i ++){
              var item = this.list[i];
              total += item.price * item.count;
            }
            return total.toString().replace(/\B(?=(\d{3})+$)/g, ',');
        }
    },
    methods: {
        handle: function (index) {
            if(this.list[index].count === 1) return;
            this.list[index].count--;
        },
        hadnleAdd: function (index) {
            this.list[index].count++;
        },
        handleRemove: function (index) {
            this.list.splice(index,1);
        }
    }
});