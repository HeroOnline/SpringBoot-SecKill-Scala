package cn.edu.jxnu.seckill.domain
import scala.language.implicitConversions
import scala.beans.BeanProperty

/**
 * 商品
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class Goods {

    /** BeanProperty 生成与Java兼容的set/get方法，但属性不能为private，所以我设置为包级 ，implicitConversions 支持Scala-Java隐式类型转换*/
    //商品id
    @BeanProperty
    var id: Long = _

    //商品名称
    @BeanProperty
    var goodsName: String = _

    //商品标题
    @BeanProperty
    var goodsTitle: String = _

    //商品图片
    @BeanProperty
    var goodsImg: String = _

    //商品描述
    @BeanProperty
    var goodsDetail: String = _

    //商品价格
    @BeanProperty
    var goodsPrice: String = _

    //商品库存
    @BeanProperty
    var goodsStock: String = _

    /**
     * 重写父类非抽象方法，必须加override
     *
     * 字符拼接的时候 + 不能在最前面
     */
    override def toString(): String = {
        "Goods [id=" + id + ", goodsName=" + goodsName + ", goodsTitle=" + goodsTitle +
            ", goodsImg=" + goodsImg + ", goodsDetail=" + goodsDetail + ", goodsPrice=" + goodsPrice +
            ", goodsStock=" + goodsStock + "]"
    }
}