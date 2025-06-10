<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','release_students') || $check_field('add','release_students') || $check_field('set','release_students')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布学生" prop="release_students">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_release_students(form['release_students']) }}
							<!--<el-input id="business_name" v-model="form['release_students']" placeholder="请输入发布学生"-->
							<!--v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','release_students')) || (!form['publish_topic_selection_id'] && $check_field('add','release_students'))" :disabled="disabledObj['release_students_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','release_students')">{{form['release_students']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','release_students')) || (!form['publish_topic_selection_id'] && $check_field('add','release_students'))" id="release_students" v-model="form['release_students']" :disabled="disabledObj['release_students_isDisabled']">
								<el-option v-for="o in list_user_release_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','release_students')" id="release_students" v-model="form['release_students']" :disabled="true">
								<el-option v-for="o in list_user_release_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="release_students" v-model="form['release_students']" :disabled="disabledObj['release_students_isDisabled']">
							<el-option v-for="o in list_user_release_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生姓名" prop="student_name">
												<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','student_name')) || (!form['publish_topic_selection_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布时间" prop="release_time">
								<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','release_time')) || (!form['publish_topic_selection_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','thesis_title') || $check_field('add','thesis_title') || $check_field('set','thesis_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文题目" prop="thesis_title">
												<el-input id="thesis_title" v-model="form['thesis_title']" placeholder="请输入论文题目"
							  v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','thesis_title')) || (!form['publish_topic_selection_id'] && $check_field('add','thesis_title'))" :disabled="disabledObj['thesis_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','thesis_title')">{{form['thesis_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','question_type') || $check_field('add','question_type') || $check_field('set','question_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目类型" prop="question_type">
												<el-input id="question_type" v-model="form['question_type']" placeholder="请输入题目类型"
							  v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','question_type')) || (!form['publish_topic_selection_id'] && $check_field('add','question_type'))" :disabled="disabledObj['question_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','question_type')">{{form['question_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','title_attachment') || $check_field('add','title_attachment') || $check_field('set','title_attachment')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目附件" prop="title_attachment">
												<div v-if="disabledObj['title_attachment_isDisabled']">
						<div v-if="$check_field('get','title_attachment')">
							<el-button type="primary" @click="$download($fullUrl(form['title_attachment']),form['title_attachment'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','title_attachment')) || (!form['publish_topic_selection_id'] && $check_field('add','title_attachment'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_title_attachment" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','title_attachment')">
							<el-button type="primary" @click="$download($fullUrl(form['title_attachment']),form['title_attachment'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','question_details') || $check_field('add','question_details') || $check_field('set','question_details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目详情" prop="question_details">
								<el-input type="textarea" id="question_details" v-model="form['question_details']" placeholder="请输入题目详情"
						v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','question_details')) || (!form['publish_topic_selection_id'] && $check_field('add','question_details'))" :disabled="disabledObj['question_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','question_details')">{{form['question_details']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','topic_status') || $check_field('add','topic_status') || $check_field('set','topic_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="选题状态" prop="topic_status">
								<el-select id="topic_status" v-model="form['topic_status']"
						v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','topic_status')) || (!form['publish_topic_selection_id'] && $check_field('add','topic_status'))">
						<el-option v-for="o in list_topic_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','topic_status')">{{form['topic_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','question_status') || $check_field('add','question_status') || $check_field('set','question_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="题目状态" prop="question_status">
								<el-select id="question_status" v-model="form['question_status']"
						v-if="user_group === '管理员' || (form['publish_topic_selection_id'] && $check_field('set','question_status')) || (!form['publish_topic_selection_id'] && $check_field('add','question_status'))">
						<el-option v-for="o in list_question_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','question_status')">{{form['question_status']}}</div>
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
				field: "publish_topic_selection_id",
				url_add: "~/api/publish_topic_selection/add?",
				url_set: "~/api/publish_topic_selection/set?",
				url_get_obj: "~/api/publish_topic_selection/get_obj?",
				url_upload: "~/api/publish_topic_selection/upload?",

				query: {
					"publish_topic_selection_id": 0,
				},

				form: {
								"release_students": 0, // 发布学生
										"student_name":  '', // 学生姓名
										"release_time":  '', // 发布时间
										"thesis_title":  '', // 论文题目
										"question_type":  '', // 题目类型
										"title_attachment":  '', // 题目附件
										"question_details":  '', // 题目详情
										"topic_status":  '', // 选题状态
										"question_status":  '', // 题目状态
											"publish_topic_selection_id": 0, // ID
						
				},
				disabledObj:{
								"release_students_isDisabled": false,
										"student_name_isDisabled": false,
										"release_time_isDisabled": false,
										"thesis_title_isDisabled": false,
										"question_type_isDisabled": false,
										"title_attachment_isDisabled": false,
										"question_details_isDisabled": false,
										"topic_status_isDisabled": false,
										"question_status_isDisabled": false,
										},

	
					// 用户列表
				list_user_release_students: [],
						// 用户组
				group_user_release_students: "",
				
		
		
		
		
		
						// 选题状态选项列表
				list_topic_status: ['同意选题','拒绝选题'],
	
						// 题目状态选项列表
				list_question_status: ['确认开题'],
	
	
			}
		},
		methods: {


	
	
				/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_release_students() {
                // if(this.user_group !== "管理员" && this.form["release_students"] === 0) {
                //     this.form["release_students"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_release_students = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取学生用户用户组
			 */
			async get_group_user_release_students() {
							this.form["release_students"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_release_students = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_release_students(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_release_students.source_table+"/get_obj?"
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
												_this.form["release_students"] = id
									_this.disabledObj['release_students' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "release_students") {
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
					get_user_release_students(id){
				var obj = this.list_user_release_students.getObj({"user_id":id});
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
			 * 上传题目附件
			 * @param {Object} param 文件参数
			 */
			upload_title_attachment(param){
						this.uploadFile(param.file, "title_attachment");
					},
	
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
																							
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
					bl = this.$check_action('/publish_topic_selection/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/publish_topic_selection/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/publish_topic_selection/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/publish_topic_selection/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/publish_topic_selection/view','get');
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
					this.get_list_user_release_students();
					this.get_group_user_release_students();
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
