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
import { ref, watchEffect, onMounted } from "vue";
import PostList from "@/components/PostList.vue";
import MyDivider from "@/components/MyDivider.vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();

// 从路由获取 `category` 和 `text` 查询参数
const activeKey = ref(route.params.category || "post"); // 默认为 "post"
const searchParams = ref({
  text: route.query.text || "", // 默认为空
  pageSize: 10,
  pageNum: 1,
});
const initSearchParams = {
  text: "",
  pageSize: 10,
  pageNum: 1,
};
// 监听路由的变化，更新组件状态
watchEffect(() => {
  // 更新 searchParams.text 来保持和路由的同步
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
  } as any;
  activeKey.value = route.params.category || "post";
});

// 搜索时同步路由参数
const onSearch = (value: string) => {
  searchParams.value.text = value;
  router.push({
    query: searchParams.value, // 更新查询参数
    pageSize: 10,
    pageNum: 1,
  }) as any;
};

// 选项卡切换时同步路由
const onTabChange = (key: string) => {
  activeKey.value = key;
  router.push({
    path: `/${key}`, // 切换到相应的路由路径
    query: searchParams.value, // 保持当前的搜索参数
  });
};
</script>

<style scoped></style>
