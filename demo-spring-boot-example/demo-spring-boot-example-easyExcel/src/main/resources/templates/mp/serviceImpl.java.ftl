package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.hjt.exception.bizException.BizException;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjt.util.ConditionsUtil;
/**
* <p>
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@Service
@Transactional
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    @Override
    public Page<${entity}> list${entity}sByPage(int page, int pageSize, ${entity} factor) {
        log.info("正在执行分页查询${entity?uncap_first}: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        LambdaQueryWrapper<${entity}> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        //模糊查询例子
       //lambdaQueryWrapper.like(SysUser::getNickName,factor);
        //也可以实体类进行模糊查询
        //QueryWrapper<${entity}>likeQueryWrapper = ConditionsUtil.getLikeQueryWrapper(factor);
        //Page<${entity}> result = super.page(new Page<>(page, pageSize),lambdaQueryWrapper);
        Page<${entity}> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询${entity?uncap_first}完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public ${entity} get${entity}ById(Long id) {
        log.info("正在查询${entity?uncap_first}中id为{}的数据",id);
        ${entity} ${entity?uncap_first} = super.getById(id);
        log.info("查询id为{}的${entity?uncap_first}{}",id,(null == ${entity?uncap_first}?"无结果":"成功"));
        return ${entity?uncap_first};
    }

    @Override
    public Long insert${entity}(${entity} ${entity?uncap_first}) {
        log.info("正在插入${entity?uncap_first}");
        if (super.save(${entity?uncap_first})) {
            log.info("插入${entity?uncap_first}成功,id为{}",${entity?uncap_first}.getId());
            return ${entity?uncap_first}.getId();
        } else {
            log.error("插入${entity?uncap_first}失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public Long delete${entity}ById(Long id) {
        log.info("正在删除id为{}的${entity?uncap_first}",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的${entity?uncap_first}成功",id);
            return id;
        } else {
            log.error("删除id为{}的${entity?uncap_first}失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public Long update${entity}(${entity} ${entity?uncap_first}) {
        log.info("正在更新id为{}的${entity?uncap_first}",${entity?uncap_first}.getId());
        if (super.updateById(${entity?uncap_first})) {
            log.info("更新d为{}的${entity?uncap_first}成功",${entity?uncap_first}.getId());
            return ${entity?uncap_first}.getId();
        } else {
            log.error("更新id为{}的${entity?uncap_first}失败",${entity?uncap_first}.getId());
            throw new BizException("更新失败[id=" + ${entity?uncap_first}.getId() + "]");
        }
    }

}
</#if>
