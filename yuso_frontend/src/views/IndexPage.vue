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
        <PostList :postList="postList"></PostList>
      </a-tab-pane>
      <a-tab-pane key="picture" tab="图片" force-render>
        <PictureList :picture-list="pictureList"></PictureList>
      </a-tab-pane>
      <a-tab-pane key="user" tab="用户">
        <UserList :user-list="userList"></UserList>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect, onMounted, watch } from "vue";
import PostList from "@/components/PostList.vue";
import MyDivider from "@/components/MyDivider.vue";
import { useRoute, useRouter } from "vue-router";

import myAxios from "@/plugins/myAxios";
import UserList from "@/components/UserList.vue";
import PictureList from "@/components/PictureList.vue";
import { message } from "ant-design-vue";

const router = useRouter();
const route = useRoute();

/*
获取帖子列表
 */
const postList = ref([]);
const userList = ref([]);
const pictureList = ref([]);
/**
 * 加载聚合数据
 * @param params
 */
const loadAllData = (params: any) => {
  const query = {
    ...params,
    searchText: params.text,
    type: params.type,
  };
  myAxios.post("search/all", query).then((res) => {
    postList.value = res.postList;
    userList.value = res.userList;
    pictureList.value = res.pictureList;
  });
};
/**
 * 加载单类数据
 * @param params
 */
const loadData = (params: any) => {
  const query = {
    ...params,
    searchText: params.text,
    type: params.type,
  };

  myAxios.post("search/all", query).then((res: any) => {
    const type = query.type;
    if (type === "post") {
      postList.value = res.dataList;
    } else if (type === "user") {
      userList.value = res.dataList;
    } else if (type === "picture") {
      pictureList.value = res.dataList;
    }
  });
};

// 从路由获取 `category` 和 `text` 查询参数
const activeKey = ref(route.params.category || "post"); // 默认为 "post"
const searchParams = ref({
  text: "", // 默认为空
  pageSize: 10,
  pageNum: 1,
  type: "post",
});
// 监听路由的变化，更新组件状态
watch(
  () => route.params.category,
  (newCategory) => {
    searchParams.value.type = newCategory || "post"; // 更新 searchParams.type
    activeKey.value = newCategory || "post"; // 更新 activeKey
    loadData(searchParams.value);
  },
  { immediate: true } // 初次渲染时也执行
);

// 搜索时同步路由参数
const onSearch = (value: string) => {
  searchParams.value.text = value;
  searchParams.value.type = route.params.category || "post";
  console.log(1);
  loadData(searchParams.value);
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
