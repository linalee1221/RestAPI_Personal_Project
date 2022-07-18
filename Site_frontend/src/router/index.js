import Vue from "vue";
import VueRouter from "vue-router";

// router : 메뉴달기 비교) springboot : controller (메뉴달기)
Vue.use(VueRouter);

const routes = [
  // home
  {
    path: "/",
    alias: "/home",
    name: "home",
    component: () => import("@/views/HomeView"),
  },
  // /upload
  {
    path: "/upload",
    name: "upload",
    component: () => import("@/components/UploadImage"),
  },
  // /, /tutorials 메뉴를 클릭하면 TutorialsList.vue 로딩
  // TutorialsList.vue : Tutorial 목록 화면
  {
    path: "/tutorials",
    name: "tutorials",
    component: () => import("@/components/tutorials/TutorialsList"),
  },
  // Tutorial.vue : Tutorial 상세 화면
  {
    // :id => params
    path: "/tutorials/:id",
    name: "tutorial-details",
    component: () => import("@/components/tutorials/Tutorial"),
  },
  // AddTutorial.vue : Tutorial 에 목록 추가 화면
  {
    path: "/add",
    name: "add",
    component: () => import("@/components/tutorials/AddTutorial"),
  },
  //  Customer 메뉴 달기
  {
    path: "/customers",
    name: "customers",
    component: () => import("@/components/customer/CustomerList"),
  },
  {
    path: "/customers/:id",
    name: "edit-customer",
    component: () => import("@/components/customer/EditCustomer"),
  },
  {
    path: "/add-customer",
    name: "add-customer",
    component: () => import("@/components/customer/AddCustomer"),
  },
  //  Complain 메뉴 달기
  {
    path: "/complains",
    name: "complains",
    component: () => import("@/components/complain/ComplainList"),
  },
  {
    path: "/complains/:id",
    name: "edit-complain",
    component: () => import("@/components/complain/EditComplain"),
  },
  {
    path: "/add-complain",
    name: "add-complain",
    component: () => import("@/components/complain/AddComplain"),
  },
  //  Free 메뉴 달기
  {
    path: "/frees",
    name: "frees",
    component: () => import("@/components/free/FreeList"),
  },
  {
    path: "/frees/:id",
    name: "edit-free",
    component: () => import("@/components/free/EditFree"),
  },
  {
    path: "/add-free",
    name: "add-free",
    component: () => import("@/components/free/AddFree"),
  },
  //  FAQ 메뉴 달기
  {
    path: "/faqs",
    name: "faqs",
    component: () => import("@/components/faq/FaqList"),
  },
  {
    path: "/faqs/:id",
    name: "edit-faq",
    component: () => import("@/components/faq/EditFaq"),
  },
  {
    path: "/add-faq",
    name: "add-faq",
    component: () => import("@/components/faq/AddFaq"),
  },
  //  login 관련 메뉴
  {
    path: "/login",
    component: () => import("@/views/Login")
  },
  {
    path: "/profile",
    component: () => import("@/views/Profile")
  },
  {
    path: "/register",
    component: () => import("@/views/Register")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
