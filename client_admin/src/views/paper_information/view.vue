<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','thesis_title') || $check_field('add','thesis_title') || $check_field('set','thesis_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文题目" prop="thesis_title">
												<el-input id="thesis_title" v-model="form['thesis_title']" placeholder="请输入论文题目"
							  v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','thesis_title')) || (!form['paper_information_id'] && $check_field('add','thesis_title'))" :disabled="disabledObj['thesis_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','thesis_title')">{{form['thesis_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','instructor') || $check_field('add','instructor') || $check_field('set','instructor')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="指导教师" prop="instructor">
													<el-select v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','instructor')) || (!form['paper_information_id'] && $check_field('add','instructor'))" id="instructor" v-model="form['instructor']" :disabled="disabledObj['instructor_isDisabled']">
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
								<el-col v-if="user_group === '管理员' || $check_field('get','paper_type') || $check_field('add','paper_type') || $check_field('set','paper_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文类型" prop="paper_type">
												<el-input id="paper_type" v-model="form['paper_type']" placeholder="请输入论文类型"
							  v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','paper_type')) || (!form['paper_information_id'] && $check_field('add','paper_type'))" :disabled="disabledObj['paper_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','paper_type')">{{form['paper_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','upload_students') || $check_field('add','upload_students') || $check_field('set','upload_students')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="上传学生" prop="upload_students">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_upload_students(form['upload_students']) }}
							<!--<el-input id="business_name" v-model="form['upload_students']" placeholder="请输入上传学生"-->
							<!--v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','upload_students')) || (!form['paper_information_id'] && $check_field('add','upload_students'))" :disabled="disabledObj['upload_students_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','upload_students')">{{form['upload_students']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','upload_students')) || (!form['paper_information_id'] && $check_field('add','upload_students'))" id="upload_students" v-model="form['upload_students']" :disabled="disabledObj['upload_students_isDisabled']">
								<el-option v-for="o in list_user_upload_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','upload_students')" id="upload_students" v-model="form['upload_students']" :disabled="true">
								<el-option v-for="o in list_user_upload_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="upload_students" v-model="form['upload_students']" :disabled="disabledObj['upload_students_isDisabled']">
							<el-option v-for="o in list_user_upload_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','upload_time') || $check_field('add','upload_time') || $check_field('set','upload_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="上传时间" prop="upload_time">
								<el-date-picker :disabled="disabledObj['upload_time_isDisabled']" v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','upload_time')) || (!form['paper_information_id'] && $check_field('add','upload_time'))" id="upload_time"
						v-model="form['upload_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','upload_time')">{{form['upload_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','paper_attachment') || $check_field('add','paper_attachment') || $check_field('set','paper_attachment')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文附件" prop="paper_attachment">
												<div v-if="disabledObj['paper_attachment_isDisabled']">
						<div v-if="$check_field('get','paper_attachment')">
							<el-button type="primary" @click="$download($fullUrl(form['paper_attachment']),form['paper_attachment'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','paper_attachment')) || (!form['paper_information_id'] && $check_field('add','paper_attachment'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_paper_attachment" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','paper_attachment')">
							<el-button type="primary" @click="$download($fullUrl(form['paper_attachment']),form['paper_attachment'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','thesis_evaluation') || $check_field('add','thesis_evaluation') || $check_field('set','thesis_evaluation')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文评优" prop="thesis_evaluation">
								<el-select id="thesis_evaluation" v-model="form['thesis_evaluation']"
						v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','thesis_evaluation')) || (!form['paper_information_id'] && $check_field('add','thesis_evaluation'))">
						<el-option v-for="o in list_thesis_evaluation" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','thesis_evaluation')">{{form['thesis_evaluation']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','teachers_opinion') || $check_field('add','teachers_opinion') || $check_field('set','teachers_opinion')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="老师意见" prop="teachers_opinion">
								<el-input type="textarea" id="teachers_opinion" v-model="form['teachers_opinion']" placeholder="请输入老师意见"
						v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','teachers_opinion')) || (!form['paper_information_id'] && $check_field('add','teachers_opinion'))" :disabled="disabledObj['teachers_opinion_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','teachers_opinion')">{{form['teachers_opinion']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','paper_comments') || $check_field('add','paper_comments') || $check_field('set','paper_comments')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论文评语" prop="paper_comments">
								<el-input type="textarea" id="paper_comments" v-model="form['paper_comments']" placeholder="请输入论文评语"
						v-if="user_group === '管理员' || (form['paper_information_id'] && $check_field('set','paper_comments')) || (!form['paper_information_id'] && $check_field('add','paper_comments'))" :disabled="disabledObj['paper_comments_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','paper_comments')">{{form['paper_comments']}}</div>
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
				field: "paper_information_id",
				url_add: "~/api/paper_information/add?",
				url_set: "~/api/paper_information/set?",
				url_get_obj: "~/api/paper_information/get_obj?",
				url_upload: "~/api/paper_information/upload?",

				query: {
					"paper_information_id": 0,
				},

				form: {
								"thesis_title":  '', // 论文题目
										"instructor": 0, // 指导教师
										"paper_type":  '', // 论文类型
										"upload_students": 0, // 上传学生
										"upload_time":  '', // 上传时间
										"paper_attachment":  '', // 论文附件
										"thesis_evaluation":  '', // 论文评优
										"teachers_opinion":  '', // 老师意见
										"paper_comments":  '', // 论文评语
											"paper_information_id": 0, // ID
						
				},
				disabledObj:{
								"thesis_title_isDisabled": false,
										"instructor_isDisabled": false,
										"paper_type_isDisabled": false,
										"upload_students_isDisabled": false,
										"upload_time_isDisabled": false,
										"paper_attachment_isDisabled": false,
										"thesis_evaluation_isDisabled": false,
										"teachers_opinion_isDisabled": false,
										"paper_comments_isDisabled": false,
										},

	
		
					// 用户列表
				list_user_instructor: [],
				
		
					// 用户列表
				list_user_upload_students: [],
						// 用户组
				group_user_upload_students: "",
				
		
						// 论文评优选项列表
				list_thesis_evaluation: ['优','良','中'],
	
		
		
	
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
			async get_list_user_upload_students() {
                // if(this.user_group !== "管理员" && this.form["upload_students"] === 0) {
                //     this.form["upload_students"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_upload_students = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取学生用户用户组
			 */
			async get_group_user_upload_students() {
							this.form["upload_students"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_upload_students = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_upload_students(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_upload_students.source_table+"/get_obj?"
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
												_this.form["upload_students"] = id
									_this.disabledObj['upload_students' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "upload_students") {
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
					get_user_upload_students(id){
				var obj = this.list_user_upload_students.getObj({"user_id":id});
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
			 * 上传论文附件
			 * @param {Object} param 文件参数
			 */
			upload_paper_attachment(param){
						this.uploadFile(param.file, "paper_attachment");
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
										        if (this.form["upload_time"].indexOf("-")===-1){
          this.form["upload_time"] = this.$toTime(parseInt(this.form["upload_time"]),"yyyy-MM-dd")
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


														        if(this.form["upload_time"]=="0000-00-00"){
          this.form["upload_time"] = null;
        }
				if(parseInt(this.form["upload_time"]) > 9999){
					this.form["upload_time"] = this.$toTime(parseInt(this.form["upload_time"]),"yyyy-MM-dd")
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
					bl = this.$check_action('/paper_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/paper_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/paper_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/paper_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/paper_information/view','get');
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
										this.get_list_user_upload_students();
					this.get_group_user_upload_students();
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
