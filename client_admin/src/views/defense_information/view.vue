<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','thesis_title') || $check_field('add','thesis_title') || $check_field('set','thesis_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文题目" prop="thesis_title">
												<el-input id="thesis_title" v-model="form['thesis_title']" placeholder="请输入论文题目"
							  v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','thesis_title')) || (!form['defense_information_id'] && $check_field('add','thesis_title'))" :disabled="disabledObj['thesis_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','thesis_title')">{{form['thesis_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','paper_type') || $check_field('add','paper_type') || $check_field('set','paper_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文类型" prop="paper_type">
												<el-input id="paper_type" v-model="form['paper_type']" placeholder="请输入论文类型"
							  v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','paper_type')) || (!form['defense_information_id'] && $check_field('add','paper_type'))" :disabled="disabledObj['paper_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','paper_type')">{{form['paper_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','defense_student') || $check_field('add','defense_student') || $check_field('set','defense_student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩学生" prop="defense_student">
													<el-select v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','defense_student')) || (!form['defense_information_id'] && $check_field('add','defense_student'))" id="defense_student" v-model="form['defense_student']" :disabled="disabledObj['defense_student_isDisabled']">
							<el-option v-for="o in list_user_defense_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','defense_student')" id="defense_student" v-model="form['defense_student']" :disabled="true">
							<el-option v-for="o in list_user_defense_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','defense_time') || $check_field('add','defense_time') || $check_field('set','defense_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩时间" prop="defense_time">
								<el-date-picker :disabled="disabledObj['defense_time_isDisabled']" v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','defense_time')) || (!form['defense_information_id'] && $check_field('add','defense_time'))" id="defense_time"
						v-model="form['defense_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','defense_time')">{{form['defense_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','venue_of_defense') || $check_field('add','venue_of_defense') || $check_field('set','venue_of_defense')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩地点" prop="venue_of_defense">
												<el-input id="venue_of_defense" v-model="form['venue_of_defense']" placeholder="请输入答辩地点"
							  v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','venue_of_defense')) || (!form['defense_information_id'] && $check_field('add','venue_of_defense'))" :disabled="disabledObj['venue_of_defense_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','venue_of_defense')">{{form['venue_of_defense']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','defense_notice') || $check_field('add','defense_notice') || $check_field('set','defense_notice')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩须知" prop="defense_notice">
								<el-input type="textarea" id="defense_notice" v-model="form['defense_notice']" placeholder="请输入答辩须知"
						v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','defense_notice')) || (!form['defense_information_id'] && $check_field('add','defense_notice'))" :disabled="disabledObj['defense_notice_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','defense_notice')">{{form['defense_notice']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','defense_status') || $check_field('add','defense_status') || $check_field('set','defense_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩状态" prop="defense_status">
								<el-select id="defense_status" v-model="form['defense_status']"
						v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','defense_status')) || (!form['defense_information_id'] && $check_field('add','defense_status'))">
						<el-option v-for="o in list_defense_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','defense_status')">{{form['defense_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','defense_results') || $check_field('add','defense_results') || $check_field('set','defense_results')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩结果" prop="defense_results">
								<el-input type="textarea" id="defense_results" v-model="form['defense_results']" placeholder="请输入答辩结果"
						v-if="user_group === '管理员' || (form['defense_information_id'] && $check_field('set','defense_results')) || (!form['defense_information_id'] && $check_field('add','defense_results'))" :disabled="disabledObj['defense_results_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','defense_results')">{{form['defense_results']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "defense_information_id",
				url_add: "~/api/defense_information/add?",
				url_set: "~/api/defense_information/set?",
				url_get_obj: "~/api/defense_information/get_obj?",
				url_upload: "~/api/defense_information/upload?",

				query: {
					"defense_information_id": 0,
				},

				form: {
								"thesis_title":  '', // 论文题目
										"paper_type":  '', // 论文类型
										"defense_student": 0, // 答辩学生
										"defense_time":  '', // 答辩时间
										"venue_of_defense":  '', // 答辩地点
										"defense_notice":  '', // 答辩须知
										"defense_status":  '', // 答辩状态
										"defense_results":  '', // 答辩结果
											"defense_information_id": 0, // ID
						
				},
				disabledObj:{
								"thesis_title_isDisabled": false,
										"paper_type_isDisabled": false,
										"defense_student_isDisabled": false,
										"defense_time_isDisabled": false,
										"venue_of_defense_isDisabled": false,
										"defense_notice_isDisabled": false,
										"defense_status_isDisabled": false,
										"defense_results_isDisabled": false,
										},

	
		
		
					// 用户列表
				list_user_defense_student: [],
				
		
		
						// 答辩状态选项列表
				list_defense_status: ['通过','不通过'],
	
		
	
			}
		},
		methods: {


	
	
			
	
			
	
				/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_defense_student() {
                // if(this.user_group !== "管理员" && this.form["defense_student"] === 0) {
                //     this.form["defense_student"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_defense_student = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_defense_student(id){
				var obj = this.list_user_defense_student.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
																					// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
											
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
								        if (this.form["defense_time"].indexOf("-")===-1){
          this.form["defense_time"] = this.$toTime(parseInt(this.form["defense_time"]),"yyyy-MM-dd")
        }
													$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


											        if(this.form["defense_time"]=="0000-00-00"){
          this.form["defense_time"] = null;
        }
				if(parseInt(this.form["defense_time"]) > 9999){
					this.form["defense_time"] = this.$toTime(parseInt(this.form["defense_time"]),"yyyy-MM-dd")
				}
														

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																														return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/defense_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/defense_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/defense_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/defense_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/defense_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
									this.get_list_user_defense_student();
															},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
