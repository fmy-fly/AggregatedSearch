<template>
  <div class="index-page">
    <a-input-search
      v-model:value="searchParams.text"
      placeholder="input search text"
      enter-button="Search"
      size="large"
      @search="onSearch"
    />
    <my-divider />
    <a-tabs v-model:activeKey="activeKey" @change="onTabChange">
      <a-tab-pane key="post" tab="文章">
        <PostList></PostList>
      </a-tab-pane>
      <a-tab-pane key="picture" tab="图片" force-render
        >Content of Tab Pane 2
      </a-tab-pane>
      <a-tab-pane key="user" tab="用户">Content of Tab Pane 3</a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import PostList from "@/components/PostList.vue";
import MyDivider from "@/components/MyDivider.vue";
import { useRoute, useRouter } from "vue-router";

const activeKey = ref("post");
const router = useRouter();
const route = useRoute();
alert(route.params.category);
/*
useRouter() 用于控制路由行为，useRoute() 用于获取当前路由的状态
 */
const searchParams = ref({
  text: "",
});
const onSearch = (value: string) => {
  alert(value);
  router.push({
    query: searchParams.value,
  });
};
const onTabChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: searchParams.value,
  });
};
</script>
<style scoped></style>
