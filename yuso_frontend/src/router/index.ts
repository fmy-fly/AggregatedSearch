import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/IndexPage.vue";
import IndexPage from "@/views/IndexPage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: IndexPage,
  },
  {
    path: "/:category",
    component: IndexPage,
  },
  /*
  动态路由
   */
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
