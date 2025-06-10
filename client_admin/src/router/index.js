import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},


	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},



	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
		// 文章路由
	{
		path: '/article/table',
		name: 'article_table',
		component: () => import('../views/article/table.vue'),
		meta: {
			index: 0,
			title: '文章列表'
		}
	},
	{
		path: '/article/view',
		name: 'article_view',
		component: () => import('../views/article/view.vue'),
		meta: {
			index: 0,
			title: '文章详情'
		}
	},

	// 文章分类路由
	{
		path: '/article_type/table',
		name: 'article_type_table',
		component: () => import('../views/article_type/table.vue'),
		meta: {
			index: 0,
			title: '文章分类列表'
		}
	},
	{
		path: '/article_type/view',
		name: 'article_type_view',
		component: () => import('../views/article_type/view.vue'),
		meta: {
			index: 0,
			title: '文章分类详情'
		}
	},
	
	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },



	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '公告通知列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '公告通知详情'
		}
	},


	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},

	// 学生用户路由
	{
		path: '/student_users/table',
		name: 'student_users_table',
		component: () => import('../views/student_users/table.vue'),
		meta: {
			index: 0,
			title: '学生用户列表'
		}
	},
	{
		path: '/student_users/view',
		name: 'student_users_view',
		component: () => import('../views/student_users/view.vue'),
		meta: {
			index: 0,
			title: '学生用户详情'
		}
	},
	// 教师用户路由
	{
		path: '/teacher_users/table',
		name: 'teacher_users_table',
		component: () => import('../views/teacher_users/table.vue'),
		meta: {
			index: 0,
			title: '教师用户列表'
		}
	},
	{
		path: '/teacher_users/view',
		name: 'teacher_users_view',
		component: () => import('../views/teacher_users/view.vue'),
		meta: {
			index: 0,
			title: '教师用户详情'
		}
	},
	// 发布选题路由
	{
		path: '/publish_topic_selection/table',
		name: 'publish_topic_selection_table',
		component: () => import('../views/publish_topic_selection/table.vue'),
		meta: {
			index: 0,
			title: '发布选题列表'
		}
	},
	{
		path: '/publish_topic_selection/view',
		name: 'publish_topic_selection_view',
		component: () => import('../views/publish_topic_selection/view.vue'),
		meta: {
			index: 0,
			title: '发布选题详情'
		}
	},
	// 论文选题路由
	{
		path: '/thesis_topic_selection/table',
		name: 'thesis_topic_selection_table',
		component: () => import('../views/thesis_topic_selection/table.vue'),
		meta: {
			index: 0,
			title: '论文选题列表'
		}
	},
	{
		path: '/thesis_topic_selection/view',
		name: 'thesis_topic_selection_view',
		component: () => import('../views/thesis_topic_selection/view.vue'),
		meta: {
			index: 0,
			title: '论文选题详情'
		}
	},
	// 选题信息路由
	{
		path: '/topic_information/table',
		name: 'topic_information_table',
		component: () => import('../views/topic_information/table.vue'),
		meta: {
			index: 0,
			title: '选题信息列表'
		}
	},
	{
		path: '/topic_information/view',
		name: 'topic_information_view',
		component: () => import('../views/topic_information/view.vue'),
		meta: {
			index: 0,
			title: '选题信息详情'
		}
	},
	// 论文信息路由
	{
		path: '/paper_information/table',
		name: 'paper_information_table',
		component: () => import('../views/paper_information/table.vue'),
		meta: {
			index: 0,
			title: '论文信息列表'
		}
	},
	{
		path: '/paper_information/view',
		name: 'paper_information_view',
		component: () => import('../views/paper_information/view.vue'),
		meta: {
			index: 0,
			title: '论文信息详情'
		}
	},
	// 答辩信息路由
	{
		path: '/defense_information/table',
		name: 'defense_information_table',
		component: () => import('../views/defense_information/table.vue'),
		meta: {
			index: 0,
			title: '答辩信息列表'
		}
	},
	{
		path: '/defense_information/view',
		name: 'defense_information_view',
		component: () => import('../views/defense_information/view.vue'),
		meta: {
			index: 0,
			title: '答辩信息详情'
		}
	},
	// 成绩信息路由
	{
		path: '/score_information/table',
		name: 'score_information_table',
		component: () => import('../views/score_information/table.vue'),
		meta: {
			index: 0,
			title: '成绩信息列表'
		}
	},
	{
		path: '/score_information/view',
		name: 'score_information_view',
		component: () => import('../views/score_information/view.vue'),
		meta: {
			index: 0,
			title: '成绩信息详情'
		}
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "毕业论文管理系统-admin";
	document.title = title;
})

export default router
