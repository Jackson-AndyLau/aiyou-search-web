package com.huazai.b2c.aiyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huazai.b2c.aiyou.repo.SearchResultData;
import com.huazai.b2c.aiyou.service.SearchItemService;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品查询contrller层
 *              </ul>
 * @className SearchItemController
 * @package com.huazai.b2c.aiyou.controller
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
@RequestMapping(value = "/search")
@Controller
public class SearchItemController
{

	@Autowired
	private SearchItemService searchItemService;

	@Value("${ITEM_RECORD}")
	private Integer ITEM_RECORD;

	@RequestMapping(value = "/query/list")
	public String searchItemByPage(@RequestParam(value = "q") String query,
			@RequestParam(defaultValue = "1") Integer pageNum, Model model)
	{
		// 调查询服务层获取结果
		SearchResultData resultData = searchItemService.searchItemByPage(query, pageNum, ITEM_RECORD);
		// 将数据传递到页面
		model.addAttribute("query", query);
		model.addAttribute("itemList", resultData.getItemList());
		model.addAttribute("totalPages", resultData.getPageCount());
		model.addAttribute("page", pageNum);
		return "search";
	}
}
