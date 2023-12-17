<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { onMounted } from 'vue';
import Swal from "sweetalert2";

const voteItems = ref([]);
const itemEdit = ref({});
const URL = import.meta.env.VITE_API_JAVAURL;

// 取得所有投票項目
const getVoteItem = async () => {
    const { data } = await axios.get(`${URL}voteItems`);
    voteItems.value = data;
}

// 新增項目
const addNewItem = async () => {
    try {
        const response = await axios.post(`${URL}voteItem`, itemEdit.value);
        if (response.status === 200)
            Swal.fire({
                title: '新增成功',
                icon: 'success',
                confirmButtonColor: '#3085d6',
                confirmButtonText: "確定"
            })
        itemEdit.value = {};
        getVoteItem();
    } catch (error) {
        console.error('發生錯誤：', error);
    }
}
// 更新項目
const updateItem = async () => {
    try {
        const response = await axios.put(`${URL}voteItem/${itemEdit.itemNo}`, itemEdit.value);
        getVoteItem();
        if (response.status === 200)
            Swal.fire({
                title: '更新成功',
                icon: 'success',
                confirmButtonColor: '#3085d6',
                confirmButtonText: "確定"
            })
    } catch (error) {
        console.error('發生錯誤：', error);
    }
}

// 取得單一項目
const getItem = async (itemNo) => {
    try {
        const { data } = await axios.get(`${URL}voteItem/${itemNo}`);
        itemEdit.value = data;
    } catch (error) {
        console.error('發生錯誤：', error);
    }
}

// 刪除項目
const deleteItem = async (itemNo) => {
    const result = await Swal.fire({
        title: `確認刪除`,
        text: `刪除此項目會連帶投票紀錄都刪掉，無法復原，確定要刪除嗎?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '確定',
        cancelButtonText: '取消',
    })

    if (result.isConfirmed) {
        try {
            const response = await axios.delete(`${URL}voteItem/${itemNo}`);
            if (response.status === 200) {
                Swal.fire({
                    title: '刪除成功',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: "OK"
                })
            }
            getVoteItem();
        } catch (error) {
            console.log(`error`);
        }
    }
}
onMounted(() => {
    getVoteItem();
});

</script>
<template>
    <div class="container c">
        <div class="row mb-1 mt-2">
            <div class="col-3">
                <button class="btn btn-success btn-sm mb-3" type="button" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" data-bs-whatever="@fat"><i class="bi bi-plus-lg"></i>新增</button>
            </div>
            <div class="col-6"></div>
        </div>

        <table class="table table-hover table-sm table-cu ">
            <thead>
                <tr>
                    <th scope="col" class="t1">項目編號</th>
                    <th scope="col">投票項目</th>
                    <th scope="col">動作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in voteItems" :key="item.itemNo">

                    <td class="t1">{{ item.itemNo }}</td>
                    <td>{{ item.itemName }}</td>
                    <td>
                        <!-- 互動視窗(更新) -->
                        <button class="btn btn-secondary btn-sm mx-1" data-bs-toggle="modal" data-bs-target="#Modal2"
                            data-bs-whatever="update" @click="getItem(item.itemNo)">
                            <i class="bi bi-pencil-square"></i>
                        </button>
                        <button class="btn btn-secondary btn-sm mx-1" @click="deleteItem(item.itemNo)">
                            <i class="bi bi-trash3"></i>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- 互動式視窗 (新增) -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLabel">新增投票項目</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form @submit.prevent="addNewItem">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col mb-3">
                                    <label for="itemName" class="form-label"><span style="color:red">*</span>投票項目</label>
                                    <input type="text" id="itemName" class="form-control" aria-describedby="itemName"
                                        v-model="itemEdit.itemName" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">離開</button>
                                <button type="submit" class="btn btn-success">新增</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- 互動式視窗 (更新) -->
        <div class="modal fade" id="Modal2" tabindex="-1" aria-labelledby="Modal2" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title " id="Modal2">更新投票項目</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form @submit.prevent="updateItem">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col mb-3 hidden-column">
                                    <label for="itemNo" class="form-label ">項目編號</label>
                                    <input type="text" id="itemNo" class="form-control " aria-describedby="itemNo"
                                        v-model="itemEdit.itemNo" readonly>
                                </div>
                                <div class="col mb-3">
                                    <label for="itemName" class="form-label"><span style="color:red">*</span>投票項目</label>
                                    <input type="text" id="itemName" class="form-control" aria-describedby="itemName"
                                        v-model="itemEdit.itemName">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">離開</button>
                            <button type="submit" class="btn btn-success">儲存</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.c {
    width: 50%
}

.table-cu {
    border: 1px solid #dee2e6
}

.table-cu th,
.table-cu td {
    border: 1px solid #dee2e6;
    width: 40px;
    text-align: center;
    padding-top: 10px;
    padding-bottom: 10px;
}

.t1 {
    width: 3px !important;
    text-align: center;
}
</style>