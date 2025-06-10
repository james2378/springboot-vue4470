<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','thesis_title') || $check_field('add','thesis_title') || $check_field('set','thesis_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文题目" prop="thesis_title">
												<el-input id="thesis_title" v-model="form['thesis_title']" placeholder="请输入论文题目"
							  v-if="user_group === '管理员' || (form['score_information_id'] && $check_field('set','thesis_title')) || (!form['score_information_id'] && $check_field('add','thesis_title'))" :disabled="disabledObj['thesis_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','thesis_title')">{{form['thesis_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','question_type') || $check_field('add','question_type') || $check_field('set','question_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目类型" prop="question_type">
												<el-input id="question_type" v-model="form['question_type']" placeholder="请输入题目类型"
							  v-if="user_group === '管理员' || (form['score_information_id'] && $check_field('set','question_type')) || (!form['score_information_id'] && $check_field('add','question_type'))" :disabled="disabledObj['question_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','question_type')">{{form['question_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','defense_student') || $check_field('add','defense_student') || $check_field('set','defense_student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="答辩学生" prop="defense_student">
													<el-select v-if="user_group === '管理员' || (form['score_information_id'] && $check_field('set','defense_student')) || (!form['score_information_id'] && $check_field('add','defense_student'))" id="defense_student" v-model="form['defense_student']" :disabled="disabledObj['defense_student_isDisabled']">
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
								<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布时间" prop="release_time">
								<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['score_information_id'] && $check_field('set','release_time')) || (!form['score_information_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','award_and_excellence_evaluation') || $check_field('add','award_and_excellence_evaluation') || $check_field('set','award_and_excellence_evaluation')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评奖评优" prop="award_and_excellence_evaluation">
								<el-select id="award_and_excellence_evaluation" v-model="form['award_and_excellence_evaluation']"
						v-if="user_group === '管理员' || (form['score_information_id'] && $check_field('set','award_and_excellence_evaluation')) || (!form['score_information_id'] && $check_field('add','award_and_excellence_evaluation'))">
						<el-option v-for="o in list_award_and_excellence_evaluation" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','award_and_excellence_evaluation')">{{form['award_and_excellence_evaluation']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','score_details') || $check_field('add','score_details') || $check_field('set','score_details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="成绩详情" prop="score_details">
								<el-input type="textarea" id="score_details" v-model="form['score_details']" placeholder="请输入成绩详情"
						v-if="user_group === '管理员' || (form['score_information_id'] && $check_field('set','score_details')) || (!form['score_information_id'] && $check_field('add','score_details'))" :disabled="disabledObj['score_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','score_details')">{{form['score_details']}}</div>
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
				field: "score_information_id",
				url_add: "~/api/score_information/add?",
				url_set: "~/api/score_information/set?",
				url_get_obj: "~/api/score_information/get_obj?",
				url_upload: "~/api/score_information/upload?",

				query: {
					"score_information_id": 0,
				},

				form: {
								"thesis_title":  '', // 论文题目
										"question_type":  '', // 题目类型
										"defense_student": 0, // 答辩学生
										"release_time":  '', // 发布时间
										"award_and_excellence_evaluation":  '', // 评奖评优
										"score_details":  '', // 成绩详情
											"score_information_id": 0, // ID
						
				},
				disabledObj:{
								"thesis_title_isDisabled": false,
										"question_type_isDisabled": false,
										"defense_student_isDisabled": false,
										"release_time_isDisabled": false,
										"award_and_excellence_evaluation_isDisabled": false,
										"score_details_isDisabled": false,
										},

	
		
		
					// 用户列表
				list_user_defense_student: [],
				
						// 评奖评优选项列表
				list_award_and_excellence_evaluation: ['优','良','中'],
	
		
	
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
								        if (this.form["release_time"].indexOf("-")===-1){
          this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
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


											        if(this.form["release_time"]=="0000-00-00"){
          this.form["release_time"] = null;
        }
				if(parseInt(this.form["release_time"]) > 9999){
					this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
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
					bl = this.$check_action('/score_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/score_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/score_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/score_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/score_information/view','get');
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
