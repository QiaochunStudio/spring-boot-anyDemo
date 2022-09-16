import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import PaySuccess from '../views/PaySuccess';

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/paySuccess',
            name: 'PaySuccess',
            component: PaySuccess
        },
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
        },
    ]
})
