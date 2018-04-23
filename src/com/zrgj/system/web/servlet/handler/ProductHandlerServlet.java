package com.zrgj.system.web.servlet.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zrgj.bean.Product;
import com.zrgj.bean.ProductType;
import com.zrgj.system.web.servlet.BaseServlet;
import com.zrgj.utils.FileUtils;

/**
 * 商品处理
 */
@WebServlet("/sys/servlet/handler/productHandlerServlet")
public class ProductHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = 7682941860325280907L;

	/**
	 * 商品添加
	 */

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1、自己去构造一个对象
		Product product = new Product();

		// 2、文件上传
		try {
			upload(req, product);

			// 3、向数据库中真正的添加商品
			productService.addProduct(product);

			req.setAttribute("message", "商品添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "商品添加失败");
		}
		req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
	}

	// 文件上传
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest req, Product product) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		if (!ServletFileUpload.isMultipartContent(req)) {
			req.setAttribute("message", "此表单有问题");
			return;
		}

		// 这个乱码问题解决的是什么问题?解决的上传的文件的文件名中文问题
		upload.setHeaderEncoding("UTF-8");

		// 解析请求
		List<FileItem> fileItems = null;
		try {
			fileItems = upload.parseRequest(req);

			if (fileItems != null && fileItems.size() > 0) {

				// 表示的是文件上传的路径(目录)
				String saveDir = this.getServletContext().getRealPath("/upload");

				for (FileItem item : fileItems) {

					// 判断item是文件上传域还是普通输入域
					if (item.isFormField()) {
						// 表示的是普通输入域
						String filedName = item.getFieldName(); // pname
						String filedValue = item.getString("UTF-8"); // value
						BeanUtils.setProperty(product, filedName, filedValue);
						if ("subProductTypeId".equals(filedName)) {
							ProductType type = new ProductType();
							type.setId(Integer.parseInt(filedValue));
							product.setProductType(type);
						}
					} else {
						// 文件上传域

						// 1、获取文件名
						String fileName = item.getName();
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

						// 2、生成唯一的文件名
						fileName = FileUtils.generateUniqueFileName(fileName);

						// 3、目录要打散
						String realSaveDir = FileUtils.generateDir(fileName, saveDir);
						FileOutputStream fos = new FileOutputStream(new File(realSaveDir, fileName));
						InputStream is = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = is.read(buffer)) != -1) {
							fos.write(buffer, 0, len);
							fos.flush();
						}
						is.close();
						fos.close();
						item.delete();

						// 4、设置product的图片路径
						product.setPimageUrl(FileUtils.getRelativeDir(fileName) + "\\" + fileName);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// --------------------删除商品-----------------

	public String delete(HttpServletRequest request, HttpServletResponse response) {

		try {

			// 1、根据商品id查询商品
			String productId = request.getParameter("productId");
			Integer pId = Integer.parseInt(productId);
			Product product = productService.queryProductById(pId);
			// 2、把这个商品的对应的图片也删除
			// 2.1 找出商品对应的图片的路径
			// 2.1.1 首先拿到是服务器上传图片主目录
			String imageDirPath = this.getServletContext().getRealPath("/upload");
			// 2.1.2 要拿到这个图片的保存在数据库中的路径
			String imagePath = product.getPimageUrl();
			File file = new File(imageDirPath, imagePath);
			if (file.exists()) {
				file.delete();
			}

			// 3、调用商品service层去根据商品id删除商品(从数据库的层面把商品数据删除)
			productService.deleteProductById(pId);

			request.setAttribute("message", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
		}
		return "f:/WEB-INF/pages/message.jsp";
	}

}
