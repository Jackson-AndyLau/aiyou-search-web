package com.huazai.aiyou.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huazai.aiyou.common.response.SearchResultData;
import com.huazai.aiyou.search.service.SearchItemService;

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

	@Value("${AIYOU_TB_ITEM_RECORD}")
	private Integer AIYOU_TB_ITEM_RECORD;

	@RequestMapping(value = "/query/list")
	public String searchItemByPage(@RequestParam(value = "q") String query,
			@RequestParam(defaultValue = "1") Integer page, Model model)
	{
		// 修改queryString编码
		try
		{
			query = new String(query.getBytes("iso8859-1"), "utf-8");
			// 调查询服务层获取结果
			SearchResultData resultData = searchItemService.searchItemByPage(query, page, AIYOU_TB_ITEM_RECORD);
			// 将数据传递到页面
			model.addAttribute("query", query);
			model.addAttribute("itemList", resultData.getItemList());
			model.addAttribute("totalPages", resultData.getPageCount());
			model.addAttribute("page", page);
			return "search";
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return "error/exception";
	}
}
