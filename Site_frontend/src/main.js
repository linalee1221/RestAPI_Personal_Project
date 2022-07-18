import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/styles.css'

// Todo : Bootstrap import 4개
import {BootstrapVue} from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)


// bootstrap
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

// vee-validate : 뷰 유효성 체크 모듈
import VeeValidate from "vee-validate";

// fontawesome-svc-icons 등 : 화면에 아이콘들 설치
import {library} from "@fortawesome/fontawesome-svg-core";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt} from "@fortawesome/free-solid-svg-icons";

// 웹 아이콘들 등록
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

// 전역 컴포넌트 설정 : 웹 아이콘
Vue.component("font-awesome-icon", FontAwesomeIcon);

// 뷰 유효성 체크 모듈 사용 설정
Vue.use(VeeValidate);

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
