package ${package.Controller};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.hjt.annotation.Log;
import com.hjt.myException.AjaxResult;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import javax.annotation.Resource;


/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version v1.0
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(value = "${entity}控制类", tags = {"${entity}控制类"})
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/api/v1/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "${entity}查询分页")
    @RequestMapping(method = RequestMethod.GET)
    public AjaxResult listByPage(int page,int pageSize,${entity} factor) {
        return AjaxResult.success(${table.serviceName?uncap_first}.list${entity}sByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "${entity}根据id查询")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AjaxResult getById(@PathVariable("id") Long id) {
        return AjaxResult.success(${table.serviceName?uncap_first}.get${entity}ById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "${entity}新增")
    @RequestMapping(method = RequestMethod.POST)
    @Log(title = "${entity}新增")
    public AjaxResult insert(@RequestBody ${entity} ${entity?uncap_first}) {
        return AjaxResult.success(${table.serviceName?uncap_first}.insert${entity}(${entity?uncap_first}));
    }

    /**
    * 删除
    */
    @ApiOperation(value = "${entity}根据id删除")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @Log(title = "${entity}根据id删除")
    public AjaxResult deleteById(@PathVariable("id") Long id) {
        return AjaxResult.success(${table.serviceName?uncap_first}.delete${entity}ById(id));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "${entity}修改")
    @RequestMapping(method = RequestMethod.PUT)
    @Log(title = "${entity}修改")
    public AjaxResult updateById(@RequestBody ${entity} ${entity?uncap_first}) {
        return AjaxResult.success(${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first}));
    }
}
</#if>