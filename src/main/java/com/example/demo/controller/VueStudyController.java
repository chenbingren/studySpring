package com.example.demo.controller;

import com.example.demo.model.bean.VueData;
import com.example.demo.model.dao.VueDataDAO;
import com.example.demo.model.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Slf4j
@RestController
public class VueStudyController {


	@Autowired
	private VueDataDAO vueDataDAO;

	/**
	 * 首页数据
	 * 提供给vueStudy项目的数据接口
	 * @param params
	 * @return
	 */
    @PostMapping(value = "/home/data")
	public Object getHomeData(
			@RequestBody(required=false) Map<String,Object> params
	){
		String type = (String)params.get("type");
		int page = Integer.parseInt(params.get("page").toString());

		System.out.println(type+"::"+page);

		ResModelSample res = new ResModelSample();

		List<VueData> ls = vueDataDAO.findByType(type);

		res.setResponse(true);
		res.setResponseCode("10099");
		res.setResponseMessage("操作成功");
		res.setData(ls);

		return res;
	}

	/**
	 * 首页，相关数据
	 * 提供给vueStudy项目的数据接口
	 * @param params
	 * @return
	 */
	@GetMapping(value = "/home/first")
	public Object getBannerAndRecommend(
			@RequestBody(required=false) Map<String,Object> params
	){
		ResModelSample res = new ResModelSample();
		List<VueData> banners = vueDataDAO.findByType("banner");
		List<VueData> recommends = vueDataDAO.findByType("recommend");
		Map map = new HashMap();
		map.put("banners",banners);
		map.put("recommends",recommends);
		res.setResponse(true);
		res.setResponseCode("10099");
		res.setResponseMessage("操作成功");
		res.setData(map);
		return res;
	}


	/**
	 * 商品明细
	 * 提供给vueStudy项目的数据接口
	 */
	@GetMapping(value = "/vue/detail")
	public Object getDetailVueDate(@RequestParam Integer pid
	){

		log.info("pid ==== {} ",pid);

		ResModelSample res = new ResModelSample();
		List<VueData> detailNavBars = vueDataDAO.findByPid(pid);


		//模拟商品列------------------------------------
		String columns[] = {"销量 1580","收藏33人"};

		/*接口返回数据封装*/
		Map map = new HashMap();
		map.put("detailNavBars",detailNavBars);
		map.put("itemInfo",getItemInfo());
		map.put("columns",columns);
		map.put("shopInfo",getShopInfo());
		map.put("paramInfo",getParamInfo());
		map.put("detailInfo",getDetailInfo());
		map.put("commentInfo",getCommentInfo());

		List<VueData> recommendList = vueDataDAO.findByType("news");
		map.put("recommendList",recommendList);


		res.setResponse(true);
		res.setResponseCode("10099");
		res.setResponseMessage("操作成功");
		res.setData(map);
		return res;
	}

	/**
	 * 获取商品细化内容
	 */
	private ItemInfo getItemInfo(){
		/**模拟商品信息表-------------**/
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setTitle("折叠的榻榻米高级床垫");
		itemInfo.setDesc("可以折叠的榻榻米床垫，收纳不占空间，正反两面都可以使用，同时纯棉材质舒适亲肤，透气性好，保持贴身换将干爽 不粘腻。");
		itemInfo.setPrice("99.99");
		itemInfo.setHighNowPrice("66.66");
		itemInfo.setOldPrice("399.99");
		itemInfo.setDiscountDesc("活动价");
		return itemInfo;
	}
	/**
	 * 获取商品信息
	 * @return
	 */
	private ShopInfo getShopInfo(){
		//模拟店铺提供服务信息--------------------
		DetailService detailService1 = new DetailService();
		detailService1.setName("全国包邮");
		DetailService detailService2 = new DetailService();
		detailService2.setName("退货补运费");
		DetailService detailService3 = new DetailService();
		detailService3.setName("7天无理由退货");
		DetailService detailService4 = new DetailService();
		detailService4.setName("72小时发货");
		List<DetailService> services = new ArrayList<DetailService>();
		services.add(detailService1);
		services.add(detailService2);
		services.add(detailService3);
		services.add(detailService4);
		//模拟店铺信息-------------------
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.setName("阿炳的店铺");
		shopInfo.setShopLogo("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1581241124&di=1a207d52c115f83fd33bfa15e406d90c&src=http://www.rensheng2.com/upload/2017/06/27/ce62a6f6e6b218ce.jpg");
		shopInfo.setCFans(17867);
		shopInfo.setCSells(61312);
		shopInfo.setCGoods(99);

		/*模拟店铺打分数表-------------------*/
		List<DetailScore> detailScores = new ArrayList<DetailScore>();
		DetailScore ds1 = new DetailScore();
		ds1.setName("描述相符");
		ds1.setIsBetter(true);
		ds1.setScore("4.8");
		DetailScore ds2 = new DetailScore();
		ds2.setName("价格合理");
		ds2.setIsBetter(true);
		ds2.setScore("5.0");
		DetailScore ds3 = new DetailScore();
		ds3.setName("质量满意");
		ds3.setIsBetter(false);
		ds3.setScore("4.3");
		detailScores.add(ds1);
		detailScores.add(ds2);
		detailScores.add(ds3);
		shopInfo.setScore(detailScores);
		shopInfo.setServices(services);

		return shopInfo;
	}

	/**
	 * 获取商品图片列表详情--图片信息这块内容封装
	 */
	private DetailInfo getDetailInfo(){

		DetailImage detailImage = new DetailImage();
		detailImage.setKey("以下是图片展示的详细信息");

		List images = new ArrayList();
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397244949&di=e2316d6c673e8c2b944b94b1ad014095&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170122%2F20170122145324_c074bd4d20c537b795f6cc97f90d9e50_2.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397244948&di=cb7c14fd744d7212a0dcb5fe8e694e77&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170425%2F20170425101359_d717912139d87b5b70a5633c7088c59a_2.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397244948&di=b37921b03dc4db2a4fb735b126a63863&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170405%2F20170405103300_c3e1fc519543c7214f77951cb7b089e7_1.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397244945&di=a6290d59155736de5fbf0db1c0b1b794&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170330%2F20170330155614_e500f1e5144b416954cecaa5d5d6a016_7.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397244944&di=b893bf709369fadd0537a5a0ff4db9ad&imgtype=0&src=http%3A%2F%2Fhimg2.huanqiu.com%2Fattachment2010%2F2013%2F0822%2F20130822075137325.jpg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397244944&di=e1ad4509d691e6fea4f0f9eae1a33554&imgtype=0&src=http%3A%2F%2Fi3.3conline.com%2Fimages%2Fpiclib%2F201208%2F16%2Fbatch%2F1%2F144568%2F13451248462813rflqb9mz6_medium.jpg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397312892&di=4a5564bbf57091f4a646da008b4d341a&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170525%2F20170525114434_464d2e5ae0aa585b96e3b771a9b435b8_1.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397344558&di=d8b4826fb5942e9c4562df9809910cb6&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170213%2F20170213165739_a6f7877b5e3417de31114a54a4f36264_6.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397344558&di=8bc8a34a53ad4c0329db04e4e873c2b8&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170113%2F20170113135417_94f2d9e85d6259397f2ec4e6cadcca7c_12.jpeg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397344557&di=3b2fbc456229e7da1c24cff75a14681b&imgtype=0&src=http%3A%2F%2Fimg.gove.cn%2Ff%2F2017%2F08%2F02%2F391c1a21f0cd2724_640.jpg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397392986&di=b3c706f05e44f3e6550472bd21ab3381&imgtype=0&src=http%3A%2F%2Fy0.ifengimg.com%2Fifengimcp%2Fpic%2F20140822%2Fd69e0188b714ee789e97_size87_w800_h1227.jpg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581397392985&di=3bec034095ca4411b339f7ab79c657cf&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170207%2F20170207100930_7a3d2694bf831b3a29d61f0ee2f342d0_8.jpeg");
		detailImage.setList(images);

		List<DetailImage> detailImages = new ArrayList<DetailImage>();
		detailImages.add(detailImage);

		DetailInfo detailInfo = new DetailInfo();
		detailInfo.setDesc("描述哈哈哈，描述呵呵呵，描述嘻嘻嘻");
		detailInfo.setDetailImage(detailImages);

		return detailInfo;
	}

	/**
	 * 获取商品参数信息
	 */
	private ParamInfo getParamInfo(){
		//商品信息--里面产品详情
		List<ParamInfoDetail> paramInfoDetails = new ArrayList<ParamInfoDetail>();
		paramInfoDetails.add(new ParamInfoDetail("图案1","民族复古图|哈哈哈"));
		paramInfoDetails.add(new ParamInfoDetail("袖长1","长袖"));
		paramInfoDetails.add(new ParamInfoDetail("材质1","棉"));
		paramInfoDetails.add(new ParamInfoDetail("布料1","黄金"));
		paramInfoDetails.add(new ParamInfoDetail("价格1","合理"));
		paramInfoDetails.add(new ParamInfoDetail("产地1","布达拉宫"));
		paramInfoDetails.add(new ParamInfoDetail("图案2","民族复古图|哈哈哈"));
		paramInfoDetails.add(new ParamInfoDetail("袖长2","长袖"));
		paramInfoDetails.add(new ParamInfoDetail("材质2","棉"));
		paramInfoDetails.add(new ParamInfoDetail("布料2","黄金"));
		paramInfoDetails.add(new ParamInfoDetail("价格2","合理"));
		paramInfoDetails.add(new ParamInfoDetail("产地2","布达拉宫"));
		paramInfoDetails.add(new ParamInfoDetail("图案3","民族复古图|哈哈哈"));
		paramInfoDetails.add(new ParamInfoDetail("袖长3","长袖"));
		paramInfoDetails.add(new ParamInfoDetail("材质3","棉"));
		paramInfoDetails.add(new ParamInfoDetail("布料3","黄金"));
		paramInfoDetails.add(new ParamInfoDetail("价格3","合理"));
		paramInfoDetails.add(new ParamInfoDetail("产地3","布达拉宫"));

		//商品信息--里面尺码详情
		DetailRule detailRule = new DetailRule();
		List tables = new ArrayList();
		List tableRules = new ArrayList();
		tableRules.add(new String[]{"尺码", "ML", "XL", "XXL"});
		tableRules.add(new String[]{"衣长", "ML", "XL", "XXL"});
		tableRules.add(new String[]{"胸围", "ML", "XL", "XXL"});
		tableRules.add(new String[]{"肩宽", "ML", "XL", "XXL"});
		tables.add(tableRules);
		detailRule.setAnchor("size_type");
		detailRule.setTables(tables);

		//商品信息--里面图片详情
		String images[] = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581351509829&di=755f7fa731e91f94cb136d65332873cb&imgtype=0&src=http%3A%2F%2Fimg.mm4000.com%2Ffile%2Fa%2F82%2Fa7da2a6fe7_800.jpg",
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581351509829&di=d398893c3cad79f1f839866555019c3b&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170303%2F20170303094555_7851ce3d965701f3201d4df2dde56592_8.jpeg"};

		/*商品信息*/
		ParamInfo paramInfo = new ParamInfo();
		paramInfo.setRule(detailRule);
		paramInfo.setSet(paramInfoDetails);
		paramInfo.setImages(images);

		return paramInfo;
	}

	/**
	 * 获取用户评论数据
	 */
	private CommentInfo getCommentInfo(){
		CommentInfo ci = new CommentInfo();
		ci.setContent("评价哈哈哈");
		ci.setCreated(System.currentTimeMillis());
		ci.setStyle("样式样式");
		List images = new ArrayList();
		images.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1036892232,2441639938&fm=26&gp=0.jpg");
		images.add("https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1212875911,2459520433&fm=26&gp=0.jpg");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581602773833&di=f33892e1ffb2aad1991ccb0fc52431b4&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fba352a9b3004e2fda9006d5b4f395a55333744352ae8c-ZqqpRL_fw658");
		images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581602773831&di=46fcce9bb2a252c2c425fdf9693c2e9e&imgtype=0&src=http%3A%2F%2Ffile.cnkang.com%2Fcnkfile1%2FM00%2F03%2F24%2Fo4YBAFilPtCACS3sAAFemEuDT_Q20.jpeg");
		ci.setImages(images);
		CommentInfoUser user = new CommentInfoUser();
		user.setAvatar("评价内容");
		user.setUname("阿炳是吧");
		ci.setUser(user);
		return ci;
	}
}
