<style type="text/css">
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
<template>
  <el-table
    ref="filterTable"
    :data="tableData.filter(data => !search || data.lawyer_name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="负责律师">
            <span>{{ props.row.lawyer_name }}</span>
          </el-form-item>
          <el-form-item label="用户名">
            <span>{{ props.row.user_name }}</span>
          </el-form-item>
          <el-form-item label="订单 ID">
            <span>{{ props.row.order_id }}</span>
          </el-form-item>
          <el-form-item label="开始时间">
            <span>{{ props.row.start }}</span>
          </el-form-item>
          <el-form-item label="订单类型">
            <span>{{ props.row.order_type }}</span>
          </el-form-item>
          <el-form-item label="是否完成">
            <span>{{ props.row.done }}</span>
          </el-form-item>
          <el-form-item label="商品描述">
            <span>{{ props.row.order_descrip }}</span>
          </el-form-item>
          <el-form-item label="结束时间">
            <span>{{ props.row.end }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="订单 ID"
      prop="order_id">
    </el-table-column>
    <el-table-column
      label="负责律师"
      prop="lawyer_name">
    </el-table-column>
    <el-table-column
      label="描述"
      prop="order_descrip">
    </el-table-column>
    <el-table-column
      prop="order_type"
      label="标签"
      width="100"
      :filters="[{ text: '债权债务', value: '债权债务' }, { text: '刑事诉讼', value: '刑事诉讼' }]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          :type="scope.row.order_type === '刑事诉讼' ? 'primary' : 'success'"
          disable-transitions>{{scope.row.order_type}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import {putRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
//  import Vue from 'vue'
//  var bus = new Vue()

  export default{
    data() {
      return {
        tableData: [{
          order_id: '12987122',
          lawyer_name: '张律师',
          order_type: '债权债务',
          order_descrip: '欠钱不还如何走法律途径',
          done: '已完成',
          user_name: '阿晨',
          start: '2020.4.6',
          end: '2020.4.9'
        },
          {
            order_id: '12987123',
            lawyer_name: '李律师',
            order_type: '刑事诉讼',
            order_descrip: '伤残等级标准',
            done: '未完成',
            user_name: '老赵',
            start: '2018.5.7',
            end: '2018.5.10'
          },
          {
            order_id: '12987125',
            lawyer_name: '王律师',
            order_type: '刑事诉讼',
            order_descrip: '电话录音可以作为证据吗',
            done: '已完成',
            user_name: '赵四',
            start: '2019.6.2',
            end: '2019.6.5'
          }],
        search: '',
        articles: [],
        selItems: [],
        loading: false,
        currentPage: 1,
        totalCount: -1,
        pageSize: 6,
        keywords: '',
        dustbinData: []
      }
    },

    methods: {
      filterTag(value, row) {
        return row.order_type === value;
      },
      searchClick(){
        this.loadBlogs(1, this.pageSize);
      },
      itemClick(row){
        this.$router.push({path: '/blogDetail', query: {aid: row.id}})
      },
      deleteMany(){
        var selItems = this.selItems;
        for (var i = 0; i < selItems.length; i++) {
          this.dustbinData.push(selItems[i].id)
        }
        this.deleteToDustBin(selItems[0].state)
      },
      //翻页
      currentChange(currentPage){
        this.currentPage = currentPage;
        this.loading = true;
        this.loadBlogs(currentPage, this.pageSize);
      },
      loadBlogs(page, count){
        var _this = this;
        var url = '';
        if (this.state == -2) {
          url = "/admin/article/all" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
        } else {
          url = "/article/all?state=" + this.state + "&page=" + page + "&count=" + count + "&keywords=" + this.keywords;
        }
        getRequest(url).then(resp=> {
          _this.loading = false;
          if (resp.status == 200) {
            _this.articles = resp.data.articles;
            _this.totalCount = resp.data.totalCount;
          } else {
            _this.$message({type: 'error', message: '数据加载失败!'});
          }
        }, resp=> {
          _this.loading = false;
          if (resp.response.status == 403) {
            _this.$message({type: 'error', message: resp.response.data});
          } else {
            _this.$message({type: 'error', message: '数据加载失败!'});
          }
        }).catch(resp=> {
          //压根没见到服务器
          _this.loading = false;
          _this.$message({type: 'error', message: '数据加载失败!'});
        })
      },
      handleSelectionChange(val) {
        this.selItems = val;
      },
      handleEdit(index, row) {
        this.$router.push({path: '/editBlog', query: {from: this.activeName,id:row.id}});
      },
      handleDelete(index, row) {
        this.dustbinData.push(row.id);
        this.deleteToDustBin(row.state);
      },
      handleRestore(index, row) {
        let _this = this;
        this.$confirm('将该文件还原到原处，是否继续？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        } ).then(() => {
          _this.loading = true;
          putRequest('/article/restore', {articleId: row.id}).then(resp=> {
            if (resp.status == 200) {
              var data = resp.data;
              _this.$message({type: data.status, message: data.msg});
              if (data.status == 'success') {
                window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
              }
            } else {
              _this.$message({type: 'error', message: '还原失败!'});
            }
            _this.loading = false;
          });
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: '已取消还原'
          });
        });
      },
      deleteToDustBin(state){
        var _this = this;
        this.$confirm(state != 2 ? '将该文件放入回收站，是否继续?' : '永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.loading = true;
          var url = '';
          if (_this.state == -2) {
            url = "/admin/article/dustbin";
          } else {
            url = "/article/dustbin";
          }
          putRequest(url, {aids: _this.dustbinData, state: state}).then(resp=> {
            if (resp.status == 200) {
              var data = resp.data;
              _this.$message({type: data.status, message: data.msg});
              if (data.status == 'success') {
                window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
              }
            } else {
              _this.$message({type: 'error', message: '删除失败!'});
            }
            _this.loading = false;
            _this.dustbinData = []
          }, resp=> {
            _this.loading = false;
            _this.$message({type: 'error', message: '删除失败!'});
            _this.dustbinData = []
          });
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: '已取消删除'
          });
          _this.dustbinData = []
        });
      }
    },
    props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
  }
</script>
