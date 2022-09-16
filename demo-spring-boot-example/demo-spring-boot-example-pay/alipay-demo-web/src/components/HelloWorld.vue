<template>
  <div class="main">
    <div class="desc" />
    <div class="content">
      <div class="box">
        <div class="left">
          <span style="color: red">*&nbsp;</span>
          <span>订单号</span>
        </div>
        <div class="right">
          <input
            v-model="payInfo.outTradeNo"
            size="small"
            placeholder="商品名称..."
          />
        </div>
      </div>
      <div class="box">
        <div class="left">
          <span style="color: red">*&nbsp;</span>
          <span>商品名称</span>
        </div>
        <div class="right">
          <input
            v-model="payInfo.subject"
            size="small"
            placeholder="商品名称..."
          />
        </div>
      </div>
      <div class="box">
        <div class="left">
          <span style="color: red">*&nbsp;</span>
          <span>商品价格</span>
        </div>
        <div class="right">
          <input
            v-model="payInfo.totalAmount"
            size="small"
            placeholder="1-99之间"
          />
        </div>
      </div>
      <div class="box">
        <div class="left">
          <span style="color: red">*&nbsp;</span>
          <span>商品描述</span>
        </div>
        <div class="right">
          <input
            v-model="payInfo.description"
            type="textarea"
            :autosize="{ minRows: 4, maxRows: 4 }"
            placeholder="请输入内容"
            maxlength="200"
            show-word-limit
          />
        </div>
      </div>
      <div style="margin-top: 20px; left: 85px">
        <button style="background-color: #536976; color: white" @click="submit">
          去支付
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      payInfo: {
        outTradeNo: "",
        subject: "",
        totalAmount: 1,
        description: "",
      },
    };
  },
  methods: {
 
    submit() {
      axios
        .post(
          // 也可以设置为自己的主机名加端口号
          "http://csp1999.natapp1.cc/order/alipay?outTradeNo=" +
            this.payInfo.outTradeNo +
            "&subject=" +
            this.payInfo.subject +
            "&totalAmount=" +
            this.payInfo.totalAmount +
            "&description=" +
            this.payInfo.description
        )
        .then((resp) => {
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          const divForm = document.getElementsByTagName("div");
          if (divForm.length) {
            document.body.removeChild(divForm[0]);
          }
          const div = document.createElement("div");
          div.innerHTML = resp.data; // data就是接口返回的form 表单字符串
          document.body.appendChild(div);
          document.forms[0].setAttribute("target", "_blank"); // 新开窗口跳转
          document.forms[0].submit();
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
<style scoped>
.box {
  width: 500px;
  display: flex;
  justify-content: flex-start;
  margin-top: 20px;
}
.left {
  width: 85px;
  padding-top: 5px;
  font-size: 15px;
}
.right {
  width: 400px;
}
</style>

