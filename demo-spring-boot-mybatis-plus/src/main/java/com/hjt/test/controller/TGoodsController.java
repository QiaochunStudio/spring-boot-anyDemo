package com.hjt.test.controller;
import com.hjt.exception.entity.dto.ResultBean;
import com.hjt.test.entity.TGoods;
import com.hjt.test.service.TGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author hjt
 * @since 2022-06-23
 * @version v1.0
 */
@RestController
@Api(value = "TGoods控制类", tags = {"TGoods控制类"})
@RequestMapping("/test/api/v1/t-goods")
public class TGoodsController {

    @Resource
    private TGoodsService tGoodsService;

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "TGoods查询分页")
    @RequestMapping(method = RequestMethod.GET)
//    @Log(title = "TGoods查询分页")
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                     TGoods tGoods) {
        return new ResultBean<>(tGoodsService.listTGoodssByPage(page, pageSize,tGoods));
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "TGoods根据id查询")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Long id) {
        return new ResultBean<>(tGoodsService.getTGoodsById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "TGoods新增")
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody TGoods tGoods) {
        return new ResultBean<>(tGoodsService.insertTGoods(tGoods));
    }



    /**
    * 删除
    */
    @ApiOperation(value = "TGoods根据id删除")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean deleteById(@PathVariable("id") Long id) {
        return  new ResultBean<>(tGoodsService.deleteTGoodsById(id));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "TGoods修改")
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody TGoods tGoods) {
        return new ResultBean<>(tGoodsService.updateTGoods(tGoods));
    }

    /**
     * 根据条件修改
     */
    @ApiOperation(value = "TGoods根据条件修改")
    @RequestMapping(method = RequestMethod.PUT,value = "/updateByCondition")
    public ResultBean<?> updateByCondition(@RequestBody TGoods tGoods) {
        return new ResultBean<>(tGoodsService.updateByCondition(tGoods));
    }

    /**
     * 批量插入
     */
    @ApiOperation(value = "批量插入")
    @RequestMapping(method = RequestMethod.POST,value = "/insertList")
    public ResultBean<?> insertList(@RequestBody List<TGoods> tGoods) {

        return new ResultBean<>(tGoodsService.insertList(tGoods));
    }

    /**
     * 批量修改
     * **/
    @ApiOperation(value = "批量修改")
    @RequestMapping(method = RequestMethod.POST,value = "/updateList")
    public ResultBean<?> updateList(@RequestBody List<TGoods> tGoods) {
        return new ResultBean<>(tGoodsService.updateList(tGoods));
    }

    /**
     * 批量删除
     * **/
    @ApiOperation(value = "批量删除")
    @RequestMapping(method = RequestMethod.POST,value = "/deleteList")
    public ResultBean<?> deleteList(@RequestBody List<Long> ids) {
        return new ResultBean<>(tGoodsService.deleteList(ids));
    }

}
