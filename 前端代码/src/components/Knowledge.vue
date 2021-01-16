<template>
  <el-container class="home_container">
    <el-header>
      <div class="home_title">在线法律平台-经济法研习所</div>
      <div class="home_userinfoContainer">
        <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo">
    {{currentUserName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
            <el-dropdown-item command="MyArticle">我的订单</el-dropdown-item>
            <el-dropdown-item command="MyHome">个人主页</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="0"
          class="el-menu-vertical-demo" style="background-color: #ECECEC" router>
          <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
            <el-submenu :index="index+''" v-if="item.children.length>1" :key="index">
              <template slot="title">
                <i :class="item.iconCls"></i>
                <span>{{item.name}}</span>
              </template>
              <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path">
                {{child.name}}
              </el-menu-item>
            </el-submenu>
            <template v-else>
              <el-menu-item :index="item.children[0].path">
                <i :class="item.children[0].iconCls"></i>
                <span slot="title">{{item.children[0].name}}</span>
              </el-menu-item>
            </template>
          </template>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <el-table
            :data="tableData"
            height="500"
            border
            style="width: 100%">
            <el-table-column
              prop="type"
              label="分类"
              width="180">
            </el-table-column>
            <el-table-column
              prop="level"
              label="级别"
              width="180">
            </el-table-column>
            <el-table-column
              prop="discrib"
              label="详情">
            </el-table-column>
          </el-table>
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>


</template>

<script>
export default {
  name: "Knowledge",
  data(){
    return{
      tableData: [{
        type: '劳动法',
        discrib: '建立劳动关系，应当订立书面劳动合同。',
        level: '1'
      }, {
        type: '劳动法',
        discrib: '用人单位招用劳动者，不得扣押劳动者的居民身份证和其他证件，不得要求劳动者提供担保或者以其他名义向劳动者收取财物。',
        level: '1'
      }, {
        type: '劳动法',
        discrib: '劳动者享有平等就业和选择职业的权利、取得劳动报酬的权利、休息休假的权利、获得劳动安全卫生保护的权利、接受职业技能培训的权利、享受社会保险和福利的权利、提请劳动争议处理的权利以及法律规定的其他劳动权利。\n' +
          '劳动者应当完成劳动任务，提高职业技能，执行劳动安全卫生规程，遵守劳动纪律和职业道德。',
        level: '1'
      }, {
        type: '民法',
        discrib: '不能辨认自己行为的精神病人是无民事行为能力人，由他的法定代理人代理民事活动。 不能完全辨认自己行为的精神病人是限制民事行为能力人，可以进行与他的精神健康状况相适应的民事活动；其他民事活动由他的法定代理人代理，或者征得他的法定代理人的同意。 \n' +
          '\n' +
          ' 不能辨认自己行为的精神病人是无民事行为能力人，由他的法定代理人代理民事活动。 不能完全辨认自己行为的精神病人是限制民事行为能力人，可以进行与他的精神健康状况相适应的民事活动；其他民事活动由他的法定代理人代理，或者征得他的法定代理人的同意。 \n' +
          '\n' +
          ' ',
        level: '2'
      }, {
        type: '民法',
        discrib: '无民事行为能力人、限制民事行为能力人的监护人是他的法定代理人。',
        level: '2'
      }, {
        type: '民法',
        discrib: '公民以他的户籍所在地的居住地为住所，经常居住地与住所不一致的，经常居住地视为住所。',
        level: '2'
      }, {
        type: '刑法',
        discrib: '凡在中华人民共和国领域外犯罪，依照本法应当负刑事责任的，虽然经过外国审判，仍然可以依照本法追究，但是在外国已经受过刑罚处罚的，可以免除或者减轻处罚。',
        level: '3'
      }, {
        type: '刑法',
        discrib: '刑罚的轻重，应当与犯罪分子所犯罪行和承担的刑事责任相适应。',
        level: '3'
      }, {
        type: '刑法',
        discrib: '外国人在中华人民共和国领域外对中华人民共和国国家或者公民犯罪，而按本法规定的最低刑为三年以上有期徒刑的，可以适用本法；但是按照犯罪地的法律不受处罚的除外。',
        level: '3'
      }, {
        type: '民事诉讼法',
        discrib: '宣判前，原告申请撤诉的，是否准许，由人民法院裁定。人民法院裁定不准许撤诉的，原告经传票传唤，无正当理由拒不到庭的，可以缺席判决。\n',
        level: '4'
      }, {
        type: '民事诉讼法',
        discrib: '财产保全采取查封、扣押、冻结或者法律规定的其他方法。人民法院保全财产后，应当立即通知被保全财产的人。\n' +
          '\n' +
          '财产已被查封、冻结的，不得重复查封、冻结。\n',
        level: '4'
      }, {
        type: '民事诉讼法',
        discrib: '当事人之间恶意串通，企图通过诉讼、调解等方式侵害他 人合法权益的，人民法院应当驳回其请求，并根据情节轻重予以罚款、拘留； 构成犯罪的，依法追究刑事责任。\n',
        level: '4'
      }]

    }
  }
}
</script>

<style scoped>
.home_container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.el-header {
  background-color: #20a0ff;
  color: #333;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.el-aside {
  background-color: #ECECEC;
}

.el-main {
  background-color: #fff;
  color: #000;
  text-align: center;
}

.home_title {
  color: #fff;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}

.home_userinfoContainer {
  display: inline;
  margin-right: 20px;
}
</style>
