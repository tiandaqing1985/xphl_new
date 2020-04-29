package com.ruoyi.system.service.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ruoyi.system.service.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAssetDataMapper;
import com.ruoyi.system.mapper.XzAssetTypeMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.mapper.XzPersonalAssetMapper;
import com.ruoyi.system.domain.XzAssetData;
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzAsstesSta;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

import javax.imageio.ImageIO;

/**
 * 资产 服务层实现
 *
 * @author ruoyi
 * @date 2019-08-02
 */
@Service
public class XzAsstesServiceImpl implements IXzAsstesService {
    @Autowired
    private XzAsstesMapper xzAsstesMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private XzAssetTypeMapper xzAssetTypeMapper;

    @Autowired
    private XzAssetDataMapper xzAssetDataMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private XzPersonalAssetMapper xzPersonalAssetMapper;

    @Autowired
    private SeqService seqService;

    /**
     * 查询资产信息
     *
     * @param id 资产ID
     * @return 资产信息
     */
    @Override
    public XzAsstes selectXzAsstesById(Long id) {
        return xzAsstesMapper.selectXzAsstesById(id);
    }

    /**
     * 查询资产列表
     *
     * @param xzAsstes 资产信息
     * @return 资产集合
     */
    @Override
    public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes) {
//		xzAsstes.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        return xzAsstesMapper.selectXzAsstesList(xzAsstes);
    }

    @Override
    public boolean createQrCode(String id) {

        final int width = 300;
        final int height = 300;
        final String format = "png";
        String address = "192.168.104.252";
        final String content = "http://" + address + "/system/xzAsstes/assetsCheck/" + id;
        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            File dir = new File("qrCode");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File qrCode = new File("qrCode/" + id + ".png");
            Path file = qrCode.toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);


            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            //创建一个带透明色的BufferedImage对象
            BufferedImage outImage = new BufferedImage(width, height+10, BufferedImage.TYPE_INT_ARGB);
            Graphics2D outg = outImage.createGraphics();
            setGraphics2D(outg);

            // 画二维码到新的面板
            outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            // 画文字到新的面板
            Color color = new Color(183, 183, 183);
            outg.setColor(color);
            // 字体、字型、字号
            outg.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            //文字长度
            int strWidth = outg.getFontMetrics().stringWidth(id);
            //总长度减去文字长度的一半  （居中显示）
            int wordStartX = (width - strWidth) / 2;
            //height + (outImage.getHeight() - height) / 2 + 12
            int wordStartY = height + 10;
            // 画文字
            outg.drawString(id, wordStartX, wordStartY);
            outg.dispose();
            outImage.flush();
            image = outImage;
            image.flush();
            ImageIO.write(image, "png", qrCode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置 Graphics2D 属性  （抗锯齿）
     *
     * @param graphics2D
     * 
     */
    private static void setGraphics2D(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        graphics2D.setStroke(s);
    }

    /**
     * 新增资产
     *
     * @param xzAsstes 资产信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertXzAsstes(XzAsstes xzAsstes) {
        XzAsstes x = new XzAsstes();
        x.setAnnex(xzAsstes.getAnnex());
        x.setAssetsModel(xzAsstes.getAssetsModel());
        x.setAssetsName(xzAsstes.getAssetsName());
        x.setAssetsStatus(xzAsstes.getAssetsStatus());
        x.setAssetsType(xzAsstes.getAssetsType());
        x.setAssetsType2(xzAsstes.getAssetsType2());
        x.setAttach(xzAsstes.getAttach());
        x.setBrand(xzAsstes.getBrand());
        x.setBuyAddress(xzAsstes.getBuyAddress());
        x.setBuyDate(xzAsstes.getBuyDate());
        x.setCreateBy(xzAsstes.getCreateBy());
        x.setCreateTime(xzAsstes.getCreateTime());
        x.setExtendContent(xzAsstes.getExtendContent());
        x.setExtendMoney(xzAsstes.getExtendMoney());
        x.setExtendTime(xzAsstes.getExtendTime());
        x.setInvoiceNum(xzAsstes.getInvoiceNum());
        x.setInvoiceType(xzAsstes.getInvoiceType());
        x.setPrice(xzAsstes.getPrice());
        x.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        x.setPurchaseNum(xzAsstes.getPurchaseNum());
        x.setRegion(xzAsstes.getRegion());
        x.setRemarks(xzAsstes.getRemarks());
        x.setSort(xzAsstes.getSort());
        x.setSubmitType(xzAsstes.getSubmitType());
        x.setUnit(xzAsstes.getUnit());
        x.setUseStatus(xzAsstes.getUseStatus());
        x.setIsReturn(xzAsstes.getIsReturn());
        x.setIsRepair(xzAsstes.getIsRepair());
        try {
            // 固定资产
            if (xzAsstes.getSort().equals("1")) {
                if (xzAsstes.getCategory() == null || xzAsstes.getCategory() == "") {
                    x.setCategory(xzAsstes.getCategory());
                } else {
                    String ca = xzAsstes.getCategory().replace("，", ",");
                    List<String> category = Arrays.asList(ca.split(","));
                    String[] split = ca.split(",");
                    // 提交状态为1时，说明是保存数据
                    if (xzAsstes.getSubmitType().equals("1")) {
                        if (Integer.parseInt(xzAsstes.getCount()) == split.length) {
                            for (int sn = 0; sn < category.size(); sn++) {
                                x.setId(null);
                                x.setCount("1");
                                x.setCategory(category.get(sn));
                                System.out.println(category.get(sn) + "*************************");
                                xzAsstesMapper.insertXzAsstes(x);
                            }

                        } else {
                            // 错误返回1
                            return "物品数量和s/n码数量不一致";
                        }

                    }
                    // 提交状态为2时，说明是数据提交入库
                    if (xzAsstes.getSubmitType().equals("2")) {
                        if (Integer.parseInt(xzAsstes.getCount()) == split.length) {
                            for (int sn = 0; sn < category.size(); sn++) {
                                x.setId(null);
                                x.setCount("1");
                                x.setCategory(category.get(sn));
                                // 需要加上资产编码
                                x.setAssetsCode(createCode(xzAsstes));
                                // 提交人、 提交时间
                                x.setSubBy(xzAsstes.getSubBy());
                                x.setSubTime(xzAsstes.getSubTime());
                                xzAsstesMapper.insertXzAsstes(x);
                            }
                        } else {
                            // 错误返回1
                            return "物品数量和s/n码数量不一致";
                        }
                    }
                }

            }
            if (xzAsstes.getSort().equals("2")) {
                // 办公资产
                if (xzAsstes.getSubmitType().equals("1")) {
                    for (int i = 0; i < Integer.parseInt(xzAsstes.getCount()); i++) {
                        x.setId(null);
                        x.setCount("1");
                        xzAsstesMapper.insertXzAsstes(x);
                    }

                }

                // 提交状态为2时，说明是数据提交入库
                if (xzAsstes.getSubmitType().equals("2")) {
                    for (int i = 0; i < Integer.parseInt(xzAsstes.getCount()); i++) {
                        x.setId(null);
                        x.setCount("1");
                        // 需要加上资产编码
                        x.setAssetsCode(createCode(xzAsstes));
                        // 提交人、 提交时间
                        x.setSubBy(xzAsstes.getSubBy());
                        x.setSubTime(xzAsstes.getSubTime());
                        xzAsstesMapper.insertXzAsstes(x);
                    }
                }
            }

            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 新增资产
     *
     * @param xzAsstes 资产信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertStaAsstes(XzAsstes xzAsstes) {
        XzAsstes x = new XzAsstes();
        x.setAnnex(xzAsstes.getAnnex());
        x.setAssetsModel(xzAsstes.getAssetsModel());
        x.setAssetsName(xzAsstes.getAssetsName());
        x.setAssetsStatus(xzAsstes.getAssetsStatus());
        x.setAssetsType(xzAsstes.getAssetsType());
        x.setAssetsType2(xzAsstes.getAssetsType2());
        x.setBrand(xzAsstes.getBrand());
        x.setBuyAddress(xzAsstes.getBuyAddress());
        x.setBuyDate(xzAsstes.getBuyDate());
        x.setCreateBy(xzAsstes.getCreateBy());
        x.setCreateTime(xzAsstes.getCreateTime());
        x.setInvoiceNum(xzAsstes.getInvoiceNum());
        x.setInvoiceType(xzAsstes.getInvoiceType());
        x.setPrice(xzAsstes.getPrice());
        x.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        x.setPurchaseNum(xzAsstes.getPurchaseNum());
        x.setRegion(xzAsstes.getRegion());
        x.setRemarks(xzAsstes.getRemarks());
        x.setSort(xzAsstes.getSort());
        x.setSubmitType(xzAsstes.getSubmitType());
        x.setUnit(xzAsstes.getUnit());
        x.setUseStatus(xzAsstes.getUseStatus());
        try {
            for (int i = 0; i < Integer.parseInt(xzAsstes.getCount()); i++) {

                x.setCount("1");
                xzAsstesMapper.insertXzAsstes(x);
            }

            return "录入成功";

        } catch (Exception e) {
            e.printStackTrace();
            return "录入失败";
        }

    }

    /**
     * 修改资产
     *
     * @param xzAsstes 资产信息
     * @return 结果
     */
    @Override
    public int updateXzAsstes(XzAsstes xzAsstes) {

        // 判断此条资产有无资产编码（有 代表是提交后修改；无 代表是未提交资产修改 保存/提交）
        if (xzAsstes.getAssetsCode() == null || xzAsstes.getAssetsCode().isEmpty()) {
            if ("2".equals(xzAsstes.getSubmitType())) {
                xzAsstes.setAssetsCode(createCode(xzAsstes));
            }
        }
        return xzAsstesMapper.updateXzAsstes(xzAsstes);

    }

    /**
     * 删除资产对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteXzAsstesByIds(String ids) {
        return xzAsstesMapper.deleteXzAsstesByIds(Convert.toStrArray(ids));
    }

    /**
     * 数据批量导入（excel）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importXzAsstes(List<XzAsstes> assetsList, boolean updateSupport, String operName) {

        if (StringUtils.isNull(assetsList) || assetsList.size() == 0) {
            throw new BusinessException("导入资产数据不能为空！");
        }
        try {
			/*if (null != assetsList && assetsList.size() > 0) {
				int pointsDataLimit = 10000;// 限制条数
				Integer size = assetsList.size();
				// 判断是否有必要分批
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
					for (int i = 0; i < part; i++) {
						// 1000条
						List<XzAsstes> listPage = assetsList.subList(0, pointsDataLimit);
						xzAsstesMapper.batchInsert(listPage);
						// 剔除
						assetsList.subList(0, pointsDataLimit).clear();
					}
					if (!assetsList.isEmpty()) {
						// 表示最后剩下的数据
						xzAsstesMapper.batchInsert(assetsList);
					}
				} else {
					xzAsstesMapper.batchInsert(assetsList);
				}
			}*/
            if (null != assetsList && assetsList.size() > 0) {
                for (int i = 0; i < assetsList.size(); i++) {
                    XzAsstes x = new XzAsstes();
                    x.setAssetsModel(assetsList.get(i).getAssetsModel());
                    x.setAssetsName(assetsList.get(i).getAssetsName());
                    x.setAssetsStatus("2");
                    x.setUseStatus("1");
                    if (assetsList.get(i).getRegionName().equals("北京")) {
                        x.setRegion("1");
                        x.setAttach("1");
                    } else if (assetsList.get(i).getRegionName().equals("上海")) {
                        x.setRegion("2");
                        x.setAttach("2");
                    } else if (assetsList.get(i).getRegionName().equals("广州")) {
                        x.setRegion("3");
                        x.setAttach("3");
                    } else if (assetsList.get(i).getRegionName().equals("新疆")) {
                        x.setRegion("4");
                        x.setAttach("4");
                    } else {
                        x.setRegion(assetsList.get(i).getRegionName());
                    }
                    XzAssetType type = xzAssetTypeMapper.selectXzAssetTypeByName("1", assetsList.get(i).getAssetsTypeName());
                    x.setAssetsType(type.getId());
                    XzAssetData data = new XzAssetData();
                    data.setParentId(type.getId());
                    data.setName(assetsList.get(i).getAssetsType2Name());
                    XzAssetData type2 = xzAssetDataMapper.selectXzAssetDataByName(data);
                    x.setAssetsType2(type2.getId());
                    x.setAssetsCode(createCode(x));
                    if (assetsList.get(i).getBrandName() != null || assetsList.get(i).getBrandName() != "") {
                        String b = dictDataMapper.selectDictValue("xz_assets_brand", assetsList.get(i).getBrandName());
                        x.setBrand(b);
                    }
                    if (assetsList.get(i).getUnitName() != null || assetsList.get(i).getUnitName() != "") {
                        String u = dictDataMapper.selectDictValue("xz_assets_unit", assetsList.get(i).getUnitName());
                        x.setBrand(u);
                    }
                    if (assetsList.get(i).getInvoiceTypeName() != null || assetsList.get(i).getInvoiceTypeName() != "") {
                        String iv = dictDataMapper.selectDictValue("xz_assets_invoiceType", assetsList.get(i).getInvoiceTypeName());
                        x.setInvoiceType(iv);
                    }
                    x.setBuyAddress(assetsList.get(i).getBuyAddress());
                    x.setBuyDate(assetsList.get(i).getBuyDate());
                    x.setCategory(assetsList.get(i).getCategory());
                    x.setCount("1");
                    x.setCreateBy(operName);
                    x.setCreateTime(new Date());
                    x.setExtendContent(assetsList.get(i).getExtendContent());
                    x.setExtendMoney(assetsList.get(i).getExtendMoney());
                    x.setExtendTime(assetsList.get(i).getExtendTime());
                    x.setInvoiceNum(assetsList.get(i).getInvoiceNum());
                    x.setInvoiceType(assetsList.get(i).getInvoiceType());
                    x.setPrice(assetsList.get(i).getPrice());
                    x.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(assetsList.get(i).getPurchaseName()));
                    x.setPurchaseNum(assetsList.get(i).getPurchaseNum());
                    x.setRemarks(assetsList.get(i).getRemarks());
                    x.setSort("1");
                    x.setSubmitType("2");
                    x.setSubBy(operName);
                    x.setSubTime(new Date());

                    //新增
                    xzAsstesMapper.insertXzAsstes(x);

                }
            }

            return "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败";
        }

    }

    @Override
    @Transactional
    public String updateXzAsstesByAllDraw(Long userId) {
        XzAsstes xzAsstes = new XzAsstes();
        xzAsstes.setUseBy(userId);
        xzAsstes.setUseTime(new Date());
        // 修改固定资产状态
        xzAsstesMapper.updateXzAsstesByAssDraw(xzAsstes);
        // 修改办公资产状态
        xzAsstesMapper.updateXzAsstesByStaDraw(xzAsstes);

        //修改该用户所有个人资产未领取信息状态
        XzPersonalAsset xzPersonalAsset = new XzPersonalAsset();
        xzPersonalAsset.setUserId(userId);
        xzPersonalAssetMapper.updateByAssetUserId(xzPersonalAsset);
        return "领取成功";
    }

    /**
     * 查询数量
     */
    @Override
    public int countXzAsstesByAllDraw(Long userId) {
        return xzAsstesMapper.countXzAsstesByAllDraw(userId);
    }

    /**
     * 修改资产使用状态
     */
    @Override
    @Transactional
    public String updateXzAsstesByAssetId(XzAsstes xzAsstes) {
        XzAsstes selectResult = xzAsstesMapper.selectXzAsstesById(xzAsstes.getId());
        String sort = selectResult.getSort();
        if (("1").equals(sort) || "true".equals(selectResult.getIsReturn())) {
            xzAsstesMapper.updateXzAsstesByAssId(xzAsstes);
        } else if (("2").equals(sort)) {
            xzAsstesMapper.updateXzAsstesByStaId(xzAsstes);
        } else {

        }
        //修改该用户所有个人资产未领取信息状态
        XzPersonalAsset xzPersonalAsset = new XzPersonalAsset();
        xzPersonalAsset.setUserId(xzAsstes.getUseBy());
        xzPersonalAsset.setAssetId(xzAsstes.getId());
        xzPersonalAssetMapper.updateByAssetId(xzPersonalAsset);
        return "领取成功";
    }

    @Override
    public String selectMaxCodeByType(XzAsstes xzAsstes) {
        return xzAsstesMapper.selectMaxCodeByType(xzAsstes);
    }

    @Transactional
    public String createCode(XzAsstes xzAsstes) {

        synchronized (XzAsstesServiceImpl.class) {

            String code = null;
            String region = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            String date = format.format(new Date());

            if (xzAsstes.getRegion() != null && xzAsstes.getRegion() != "") {
                if (xzAsstes.getRegion().equals("1")) {
                    region = "BJ";
                } else if (xzAsstes.getRegion().equals("2")) {
                    region = "SH";
                } else if (xzAsstes.getRegion().equals("3")) {
                    region = "SZ";
                } else if (xzAsstes.getRegion().equals("4")) {
                    region = "XJ";
                } else if (xzAsstes.getRegion().equals("5")) {
                    region = "HZ";
                } else {

                }
            }
            XzAssetData xzAssetData = xzAssetDataMapper.selectXzAssetDataById(xzAsstes.getAssetsType2());
            // 查询类型编码
//		String a = xzAssetTypeMapper.selectXzAssetTypeById(xzAsstes.getAssetsType()).getCode();
//		String b = xzAssetDataMapper.selectXzAssetDataById(xzAsstes.getAssetsType2()).getCode();
            // 查询此编码格式编号的最大值，然后加1 如果没有，则从0001开始
            String maxNum = seqService.selectNumByType(xzAsstes.getAssetsType().toString(), xzAsstes.getAssetsType2().toString(),xzAsstes.getRegion());

            if (maxNum == null || maxNum.isEmpty()) {
                // 第一条数据
                seqService.insertSeq(xzAsstes.getAssetsType().toString(), xzAsstes.getAssetsType2().toString(),xzAsstes.getRegion());
                seqService.addSeqNumByType(xzAsstes.getAssetsType().toString(), xzAsstes.getAssetsType2().toString(),xzAsstes.getRegion());
                code = region + xzAssetData.getCode() + date + "0001";
            } else {
                // 编码加1
                int num = 10000 + Integer.parseInt(maxNum) + 1;
                seqService.addSeqNumByType(xzAsstes.getAssetsType().toString(), xzAsstes.getAssetsType2().toString(),xzAsstes.getRegion());
                // 截取：10001 截取后四位
                code = region + xzAssetData.getCode() + date + String.valueOf(num).substring(1, 5);
            }
            return code;
        }
    }

    @Override
    @Transactional
    public String updateXzAsstesBySub(String ids, XzAsstes xzAsstes) {
        try {
            List<String> id = Arrays.asList(ids.split(","));

            for (int i = 0; i < id.size(); i++) {
                XzAsstes s = xzAsstesMapper.selectXzAsstesById(Long.valueOf(id.get(i)));
                if ("1".equals(s.getSubmitType())) {
                    xzAsstes.setId(Long.valueOf(id.get(i)));
                    xzAsstes.setAssetsCode(createCode(s));
                    xzAsstesMapper.updateXzAsstes(xzAsstes);
                } else {
                    return "1";
                }
            }
            return "提交成功";
        } catch (Exception e) {
            return "提交失败";
        }

    }

    @Override
    public List<XzAsstesSta> selectXzStatisticsList(XzAsstesSta xzAsstesSta) {
        return xzAsstesMapper.selectXzStatisticsList(xzAsstesSta);
    }

    @Override
    public int selectAssetByType12(XzAsstes xz) {
        return xzAsstesMapper.selectAssetByType12(xz);
    }

}