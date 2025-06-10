<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','thesis_title') || $check_field('add','thesis_title') || $check_field('set','thesis_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文题目" prop="thesis_title">
												<el-input id="thesis_title" v-model="form['thesis_title']" placeholder="请输入论文题目"
							  v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','thesis_title')) || (!form['topic_information_id'] && $check_field('add','thesis_title'))" :disabled="disabledObj['thesis_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','thesis_title')">{{form['thesis_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','instructor') || $check_field('add','instructor') || $check_field('set','instructor')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="指导教师" prop="instructor">
													<el-select v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','instructor')) || (!form['topic_information_id'] && $check_field('add','instructor'))" id="instructor" v-model="form['instructor']" :disabled="disabledObj['instructor_isDisabled']">
							<el-option v-for="o in list_user_instructor" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','instructor')" id="instructor" v-model="form['instructor']" :disabled="true">
							<el-option v-for="o in list_user_instructor" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','question_type') || $check_field('add','question_type') || $check_field('set','question_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目类型" prop="question_type">
												<el-input id="question_type" v-model="form['question_type']" placeholder="请输入题目类型"
							  v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','question_type')) || (!form['topic_information_id'] && $check_field('add','question_type'))" :disabled="disabledObj['question_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','question_type')">{{form['question_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','selected_students') || $check_field('add','selected_students') || $check_field('set','selected_students')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="选题学生" prop="selected_students">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_selected_students(form['selected_students']) }}
							<!--<el-input id="business_name" v-model="form['selected_students']" placeholder="请输入选题学生"-->
							<!--v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','selected_students')) || (!form['topic_information_id'] && $check_field('add','selected_students'))" :disabled="disabledObj['selected_students_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','selected_students')">{{form['selected_students']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','selected_students')) || (!form['topic_information_id'] && $check_field('add','selected_students'))" id="selected_students" v-model="form['selected_students']" :disabled="disabledObj['selected_students_isDisabled']">
								<el-option v-for="o in list_user_selected_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','selected_students')" id="selected_students" v-model="form['selected_students']" :disabled="true">
								<el-option v-for="o in list_user_selected_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="selected_students" v-model="form['selected_students']" :disabled="disabledObj['selected_students_isDisabled']">
							<el-option v-for="o in list_user_selected_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','topic_selection_time') || $check_field('add','topic_selection_time') || $check_field('set','topic_selection_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="选题时间" prop="topic_selection_time">
								<el-date-picker :disabled="disabledObj['topic_selection_time_isDisabled']" v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','topic_selection_time')) || (!form['topic_information_id'] && $check_field('add','topic_selection_time'))" id="topic_selection_time"
						v-model="form['topic_selection_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','topic_selection_time')">{{form['topic_selection_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','question_status') || $check_field('add','question_status') || $check_field('set','question_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目状态" prop="question_status">
								<el-select id="question_status" v-model="form['question_status']"
						v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','question_status')) || (!form['topic_information_id'] && $check_field('add','question_status'))">
						<el-option v-for="o in list_question_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','question_status')">{{form['question_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','topic_status') || $check_field('add','topic_status') || $check_field('set','topic_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="选题状态" prop="topic_status">
								<el-select id="topic_status" v-model="form['topic_status']"
						v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','topic_status')) || (!form['topic_information_id'] && $check_field('add','topic_status'))">
						<el-option v-for="o in list_topic_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','topic_status')">{{form['topic_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','topic_notes') || $check_field('add','topic_notes') || $check_field('set','topic_notes')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="选题备注" prop="topic_notes">
								<el-input type="textarea" id="topic_notes" v-model="form['topic_notes']" placeholder="请输入选题备注"
						v-if="user_group === '管理员' || (form['topic_information_id'] && $check_field('set','topic_notes')) || (!form['topic_information_id'] && $check_field('add','topic_notes'))" :disabled="disabledObj['topic_notes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','topic_notes')">{{form['topic_notes']}}</div>
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
				field: "topic_information_id",
				url_add: "~/api/topic_information/add?",
				url_set: "~/api/topic_information/set?",
				url_get_obj: "~/api/topic_information/get_obj?",
				url_upload: "~/api/topic_information/upload?",

				query: {
					"topic_information_id": 0,
				},

				form: {
								"thesis_title":  '', // 论文题目
										"instructor": 0, // 指导教师
										"question_type":  '', // 题目类型
										"selected_students": 0, // 选题学生
										"topic_selection_time":  '', // 选题时间
										"question_status":  '', // 题目状态
										"topic_status":  '', // 选题状态
										"topic_notes":  '', // 选题备注
											"topic_information_id": 0, // ID
						
				},
				disabledObj:{
								"thesis_title_isDisabled": false,
										"instructor_isDisabled": false,
										"question_type_isDisabled": false,
										"selected_students_isDisabled": false,
										"topic_selection_time_isDisabled": false,
										"question_status_isDisabled": false,
										"topic_status_isDisabled": false,
										"topic_notes_isDisabled": false,
										},

	
		
					// 用户列表
				list_user_instructor: [],
				
		
					// 用户列表
				list_user_selected_students: [],
						// 用户组
				group_user_selected_students: "",
				
						// 题目状态选项列表
				list_question_status: ['确认开题'],
	
						// 选题状态选项列表
				list_topic_status: ['选题成功','选题失败'],
	
		
	
			}
		},
		methods: {


	
	
			
	
				/**
			 * 获取教师用户用户列表
			 */
			async get_list_user_instructor() {
                // if(this.user_group !== "管理员" && this.form["instructor"] === 0) {
                //     this.form["instructor"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师用户");
                if(json.result && json.result.list){
                    this.list_user_instructor = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_instructor(id){
				var obj = this.list_user_instructor.getObj({"user_id":id});
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
			 * 获取学生用户用户列表
			 */
			async get_list_user_selected_students() {
                // if(this.user_group !== "管理员" && this.form["selected_students"] === 0) {
                //     this.form["selected_students"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_selected_students = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取学生用户用户组
			 */
			async get_group_user_selected_students() {
							this.form["selected_students"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_selected_students = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_selected_students(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_selected_students.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["selected_students"] = id
									_this.disabledObj['selected_students' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "selected_students") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_selected_students(id){
				var obj = this.list_user_selected_students.getObj({"user_id":id});
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
										        if (this.form["topic_selection_time"].indexOf("-")===-1){
          this.form["topic_selection_time"] = this.$toTime(parseInt(this.form["topic_selection_time"]),"yyyy-MM-dd")
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


														        if(this.form["topic_selection_time"]=="0000-00-00"){
          this.form["topic_selection_time"] = null;
        }
				if(parseInt(this.form["topic_selection_time"]) > 9999){
					this.form["topic_selection_time"] = this.$toTime(parseInt(this.form["topic_selection_time"]),"yyyy-MM-dd")
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
					bl = this.$check_action('/topic_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/topic_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/topic_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/topic_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/topic_information/view','get');
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
							this.get_list_user_instructor();
										this.get_list_user_selected_students();
					this.get_group_user_selected_students();
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
