package com.ybt.common.plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ybt.common.plugin.UpYun.PARAMS;

/**
 * <p>
 * 注意：直接使用部分图片处理功能后，将会丢弃原图保存处理后的图片
 */
public class PicBucket {

	// 运行前先设置好以下三个参数
//	public static final String BUCKET_NAME = "huahua";
//	public static final String OPERATOR_NAME = "huahua";
//	public static final String OPERATOR_PWD = "huahuahua";
//	public static final String BUCKET_NAME = "huazan";
//	public static final String OPERATOR_NAME = "huazan1";
//	public static final String OPERATOR_PWD = "bjhylckj";
	public static final String BUCKET_NAME = "sunny0";
	public static final String OPERATOR_NAME = "sunny";
	public static final String OPERATOR_PWD = "upsunyun";
	

	/** 绑定的域名 */
	public static final String URL = "http://" + BUCKET_NAME
			+ ".b0.upaiyun.com";

	/** 根目录 */
	private static final String DIR_ROOT = "/";

	/** 上传到upyun的图片名 */
	private static final String PIC_NAME = "sample.jpeg";

	/** 本地待上传的测试文件 */
	/*private static final String SAMPLE_PIC_FILE = System
			.getProperty("user.dir") + "/sample.jpeg";*/
	private static final String SAMPLE_PIC_FILE = "C:/mywork/ybt/works/2015/10/5706fc8e6fae44f1a14d5c0cee6b3ad8.jpg";
	
	private static UpYun upyun = null;

	static {
		File picFile = new File(SAMPLE_PIC_FILE);

		if (!picFile.isFile()) {
			System.out.println("本地待上传的测试文件不存在!");
		}
	}

	public static void main(String[] args) throws Exception {
		// 要传到upyun后的文件路径
				String filePath = "/works/2015/10/5706fc8e6fae44f1a14d5c0cee6b3ad8.jpg";

				// 本地待上传的图片文件
				File file = new File(SAMPLE_PIC_FILE);

		upYunWriteFile(filePath,file);
		// 初始化空间
		//upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);

		// ****** 可选设置 begin ******

		// 切换 API 接口的域名接入点，默认为自动识别接入点
		// upyun.setApiDomain(UpYun.ED_AUTO);

		// 设置连接超时时间，默认为30秒
		// upyun.setTimeout(60);

		// 设置是否开启debug模式，默认不开启
		//upyun.setDebug(false);

		// ****** 可选设置 end ******

		/*
		 * 一般性操作参考文件空间的demo（FileBucketDemo.java）
		 * 
		 * 注：图片的所有参数均可以自由搭配使用
		 */

		// 1.上传文件（文件内容）
		//testWriteFile();

		// 2.图片做缩略图；若使用了该功能，则会丢弃原图
		//testGmkerl();

		// 3.图片旋转；若使用了该功能，则会丢弃原图
		//testRotate();

		// 4.图片裁剪；若使用了该功能，则会丢弃原图
		//testCrop();

	}
	/**
	 * 初始化
	 * 
	 * @throws IOException
	 */
	public static UpYun initUpYun(File file) throws IOException {
		// 初始化空间
		upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
		upyun.setDebug(false);
		upyun.setContentMD5(UpYun.md5(file));
		return upyun;
	}
	/**
	 * 普通初始化 
	 * 
	 * @throws IOException
	 */
	public static UpYun initUpYun() throws IOException {
		// 初始化空间
		upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
		upyun.setDebug(false);
		upyun.setApiDomain(UpYun.ED_AUTO);
		return upyun;
	}
	/**
	 * 上传文件
	 * 
	 * @throws IOException
	 */
	public static boolean writeFile(String filePath, byte[] datas) {
		return upyun.writeFile(filePath, datas);
	}
	/**
	 * 上传文件
	 * 
	 * @throws IOException
	 */
	public static UpYun upYunWriteFile(String filePath,File file) throws IOException {
		// 初始化空间
		upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
		upyun.setDebug(true);
		// 要传到upyun后的文件路径
		//String filePath = DIR_ROOT + PIC_NAME;

		// 本地待上传的图片文件
		//File file = new File(SAMPLE_PIC_FILE);

		// 设置待上传文件的 Content-MD5 值
		// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
		upyun.setContentMD5(UpYun.md5(file));

		// 设置待上传文件的"访问密钥"
		// 注意：
		// 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
		// 举例：
		// 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
		// 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
		upyun.setFileSecret("xxxjpg");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true);
		if(!result){
			return null;
		}
		System.out.println(filePath + " 上传" + isSuccess(result));

		// 获取上传文件后的信息（仅图片空间有返回数据）
		System.out.println("\r\n****** " + file.getName() + " 的图片信息 *******");
		// 图片宽度
		System.out.println("图片宽度:" + upyun.getPicWidth());
		// 图片高度
		System.out.println("图片高度:" + upyun.getPicHeight());
		// 图片帧数
		System.out.println("图片帧数:" + upyun.getPicFrames());
		// 图片类型
		System.out.println("图片类型:" + upyun.getPicType());
		System.out.println("****************************************\r\n");
		System.out.println();
		return upyun;
	}
	/**
	 * 图片做缩略图
	 * <p>
	 * 注意：若使用了缩略图功能，则会丢弃原图
	 * 
	 * @throws IOException
	 */
	public static UpYun upGmkerl(String filePath,File file,int index,int w,int h) throws IOException {
		// 设置缩略图的参数
		Map<String, String> params = new HashMap<String, String>();
		/*
		 * 1)VALUE_FIX_MAX("fix_max")："限定最长边，短边自适应"<br>
		 * 2)VALUE_FIX_MIN("fix_min")："限定最短边，长边自适应"<br>
		 * 3)VALUE_FIX_WIDTH_OR_HEIGHT("fix_width_or_height")："限定宽度和高度"<br>
		 * 4)VALUE_FIX_WIDTH("fix_width")："限定宽度，高度自适应"<br>
		 * 5)VALUE_FIX_HEIGHT("fix_height")："限定高度，宽度自适应"<br>
		 * 6)VALUE_FIX_BOTH("fix_both")："固定宽度和高度"<br>
		 * 7)VALUE_FIX_SCALE("fix_scale")："等比例缩放"<br>
		 * 8)VALUE_SQUARE("square")："方块图，固定高固定宽"<br>
		 */
		String wv=""+w;
		switch(index){
			case 1:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_MAX.getValue());
				break;
			case 2:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_MIN.getValue());
				break;
			case 3:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_WIDTH_OR_HEIGHT.getValue());
				wv=w+"x"+h;
				break;
			case 4:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_WIDTH.getValue());
				break;
			case 5:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_HEIGHT.getValue());
				break;
			case 6:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_BOTH.getValue());
				wv=w+"x"+h;
				break;
			case 7:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_SCALE.getValue());
				break;
			case 8:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_SQUARE.getValue());
				break;
			default:
				params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
						PARAMS.VALUE_FIX_BOTH.getValue());
			
		}

		// 设置缩略图参数值，必须搭配缩略图类型（KEY_TYPE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), wv);

		// 设置缩略图的质量，默认 95
		params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");

		// 设置缩略图的锐化，默认锐化（true）
		params.put(PARAMS.KEY_X_GMKERL_UNSHARP.getValue(), "true");

		// 若在 upyun 后台配置过缩略图版本号，则可以设置缩略图的版本名称
		// 注意：只有存在缩略图版本名称，才会按照配置参数制作缩略图，否则无效
		params.put(PARAMS.KEY_X_GMKERL_THUMBNAIL.getValue(), "small");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true, params);
		if(!result){
			return null;
		}
		System.out.println(filePath + " 制作缩略图" + isSuccess(result));
		System.out.println("可以通过该路径来访问图片：" + URL + filePath);
		return upyun;
	}
	/**
	 * 图片做缩略图
	 * <p>
	 * 注意：若使用了缩略图功能，则会丢弃原图
	 * 
	 * @throws IOException
	 */
	public static UpYun upfixWidthGmkerl(String filePath,File file,String gmkerl) throws IOException {
		// 初始化空间
			//	upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
			//	upyun.setDebug(false);
				// 要传到upyun后的文件路径
				//String filePath = DIR_ROOT + PIC_NAME;

				// 本地待上传的图片文件
				//File file = new File(SAMPLE_PIC_FILE);

				// 设置待上传文件的 Content-MD5 值
				// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
		//		upyun.setContentMD5(UpYun.md5(file));

		// 设置缩略图的参数
		Map<String, String> params = new HashMap<String, String>();

		// 设置缩略图类型，必须搭配缩略图参数值（KEY_VALUE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
				PARAMS.VALUE_FIX_WIDTH.getValue());

		// 设置缩略图参数值，必须搭配缩略图类型（KEY_TYPE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), gmkerl);

		// 设置缩略图的质量，默认 95
		params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");

		// 设置缩略图的锐化，默认锐化（true）
		params.put(PARAMS.KEY_X_GMKERL_UNSHARP.getValue(), "true");

		// 若在 upyun 后台配置过缩略图版本号，则可以设置缩略图的版本名称
		// 注意：只有存在缩略图版本名称，才会按照配置参数制作缩略图，否则无效
		params.put(PARAMS.KEY_X_GMKERL_THUMBNAIL.getValue(), "small");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true, params);
		if(!result){
			return null;
		}
		System.out.println(filePath + " 制作缩略图" + isSuccess(result));
		System.out.println("可以通过该路径来访问图片：" + URL + filePath);
		System.out.println();
		return upyun;
	}
	/**
	 * 上传文件
	 * 
	 * @throws IOException
	 */
	public static void testWriteFile() throws IOException {

		// 要传到upyun后的文件路径
		String filePath = DIR_ROOT + PIC_NAME;

		// 本地待上传的图片文件
		File file = new File(SAMPLE_PIC_FILE);

		// 设置待上传文件的 Content-MD5 值
		// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
		upyun.setContentMD5(UpYun.md5(file));

		// 设置待上传文件的"访问密钥"
		// 注意：
		// 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
		// 举例：
		// 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
		// 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
		upyun.setFileSecret("bac");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true);

		System.out.println(filePath + " 上传" + isSuccess(result));

		// 获取上传文件后的信息（仅图片空间有返回数据）
		System.out.println("\r\n****** " + file.getName() + " 的图片信息 *******");
		// 图片宽度
		System.out.println("图片宽度:" + upyun.getPicWidth());
		// 图片高度
		System.out.println("图片高度:" + upyun.getPicHeight());
		// 图片帧数
		System.out.println("图片帧数:" + upyun.getPicFrames());
		// 图片类型
		System.out.println("图片类型:" + upyun.getPicType());

		System.out.println("****************************************\r\n");

		System.out.println("若设置过访问密钥(bac)，且缩略图间隔标志符为'!'，则可以通过以下路径来访问图片：");
		System.out.println(URL + filePath + "!bac");
		System.out.println();
	}

	/**
	 * 图片做缩略图
	 * <p>
	 * 注意：若使用了缩略图功能，则会丢弃原图
	 * 
	 * @throws IOException
	 */
	public static void testGmkerl() throws IOException {

		// 要传到upyun后的文件路径
		String filePath = DIR_ROOT + "gmkerl.jpg";

		// 本地待上传的图片文件
		File file = new File(SAMPLE_PIC_FILE);

		// 设置缩略图的参数
		Map<String, String> params = new HashMap<String, String>();

		// 设置缩略图类型，必须搭配缩略图参数值（KEY_VALUE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
				PARAMS.VALUE_FIX_BOTH.getValue());

		// 设置缩略图参数值，必须搭配缩略图类型（KEY_TYPE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), "150x150");

		// 设置缩略图的质量，默认 95
		params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");

		// 设置缩略图的锐化，默认锐化（true）
		params.put(PARAMS.KEY_X_GMKERL_UNSHARP.getValue(), "true");

		// 若在 upyun 后台配置过缩略图版本号，则可以设置缩略图的版本名称
		// 注意：只有存在缩略图版本名称，才会按照配置参数制作缩略图，否则无效
		params.put(PARAMS.KEY_X_GMKERL_THUMBNAIL.getValue(), "small");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true, params);

		System.out.println(filePath + " 制作缩略图" + isSuccess(result));
		System.out.println("可以通过该路径来访问图片：" + URL + filePath);
		System.out.println();
	}

	/**
	 * 图片旋转
	 * 
	 * @throws IOException
	 */
	public static void testRotate() throws IOException {

		// 要传到upyun后的文件路径
		String filePath = DIR_ROOT + "rotate.jpg";

		// 本地待上传的图片文件
		File file = new File(SAMPLE_PIC_FILE);

		// 图片旋转功能具体可参考：http://wiki.upyun.com/index.php?title=图片旋转
		Map<String, String> params = new HashMap<String, String>();

		// 设置图片旋转：只接受"auto"，"90"，"180"，"270"四种参数
		params.put(PARAMS.KEY_X_GMKERL_ROTATE.getValue(),
				PARAMS.VALUE_ROTATE_90.getValue());

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true, params);

		System.out.println(filePath + " 图片旋转" + isSuccess(result));
		System.out.println("可以通过该路径来访问图片：" + URL + filePath);
		System.out.println();
	}

	/**
	 * 图片裁剪
	 * 
	 * @throws IOException
	 */
	public static void testCrop() throws IOException {

		// 要传到upyun后的文件路径
		String filePath = DIR_ROOT + "crop.jpg";

		// 本地待上传的图片文件
		File file = new File(SAMPLE_PIC_FILE);

		// 图片裁剪功能具体可参考：http://wiki.upyun.com/index.php?title=图片裁剪
		Map<String, String> params = new HashMap<String, String>();

		// 设置图片裁剪，参数格式：x,y,width,height
		params.put(PARAMS.KEY_X_GMKERL_CROP.getValue(), "50,50,300,300");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true, params);

		System.out.println(filePath + " 图片裁剪" + isSuccess(result));
		System.out.println("可以通过该路径来访问图片：" + URL + filePath);
		System.out.println();
	}

	private static String isSuccess(boolean result) {
		return result ? " 成功" : " 失败";
	}
}
