<script setup>
import axios from 'axios';
import { ref, computed } from 'vue';
import { onMounted } from 'vue';
import Swal from "sweetalert2";

const voteItemsState = ref([]);
const voter = ref('');
const voteRecord = ref([]);
const URL = import.meta.env.VITE_API_JAVAURL;
const nameError = ref(false);
const nameErrMsg = ref('')

// @blur
const checkNameBlur = () => {
    if (!voter.value) {
        nameError.value = true;
        nameErrMsg.value = '姓名不可空白，請輸入';
    }
};

// @input 驗整使用者輸入姓名
const checkName = () => {
    const regex = /^[^!@#$%^&*]+$/;
    if (!voter.value) {
        nameError.value = true;
        nameErrMsg.value = '姓名不可空白，請輸入';
    } else if (!regex.test(voter.value)) {
        nameError.value = true;
        nameErrMsg.value = '不可含特殊字元[!@#$%^&*])，請重新輸入';
    } else {
        nameError.value = false;
        nameErrMsg.value = '';
    }
}

// 取得所有投票項目與其累計票數
const getVoteItemState = async () => {
    const { data } = await axios.get(`${URL}voteItems/state`)
    voteItemsState.value = data;
}

// 提交投票
const submit = async () => {

    for (let i = 0; i < voteItemsState.value.length; i++) {
        if (voteItemsState.value[i].tr == true) {
            const item = {
                voter: voter.value,
                itemNo: voteItemsState.value[i].itemNo
            }
            voteRecord.value.push(item);
        }
    }

    if (voteRecord.value.length === 0) {
        alert('至少勾選一個項目');
        return;
    }
    const result = await Swal.fire({
        title: `確認投票`,
        text: `投票後無法更改`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '確定',
        cancelButtonText: '取消',
    })
    if (result.isConfirmed) {
        try {
            const response = await axios.post(`${URL}voteRecord`, voteRecord.value)
            if (response.status === 200) {
                Swal.fire({
                    title: '投票成功',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: "OK"
                })
            }
            voter.value = ('')
            voteRecord.value = ([]);
            getVoteItemState();
        } catch (error) {
            console.log(`error`);
        }
    }
}
onMounted(() => {
    getVoteItemState();
});
</script>
<template>
    <div class="container c">
        <form @submit.prevent="submit">
            <div class="row mb-3">
                <label class="col-sm-3 col-form-label label1">請輸入姓名</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="voter" required
                        v-bind:class="{ 'is-invalid': nameError }" id="validationDefault01" @input="checkName"
                        @blur="checkNameBlur">
                </div>

                <div class="col-sm-2">
                    <button type="submit" class="btn btn-primary btn-sm btn-success" @click="confirm">提交投票</button>
                </div>
                <div class="nameError"><span class="error">{{ nameErrMsg }}</span></div>
            </div>
            <table class="table table-hover table-sm table-cu ">
                <tr>
                    <th class="t1">勾選</th>
                    <th>投票項目</th>
                    <th>目前票數小計</th>
                </tr>
                <tr v-for="(item, index) in voteItemsState" :key="index">
                    <td class="t1">
                        <input class="form-check-input flex-shrink-0" type="checkbox" :value="item.itemNo"
                            v-model="item.tr">
                    </td>
                    <td>
                        {{ item.itemName }}
                    </td>
                    <td>
                        {{ item.subtotal }}
                    </td>
                </tr>
            </table>
        </form>
    </div>
</template>
<style scoped>
.c {
    margin-top: 25px;
    width: 50%;
}

.head {
    text-align: center;
    margin-bottom: 20px;
}

.nameError {
    color: red;
    margin-bottom: 10px;
    text-align: center;
    align-items: center;
}

.error {
    text-align: center;
}

.table-cu {
    border: 1px solid #dee2e6;
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

.label1 {
    text-align: center;
}
</style>