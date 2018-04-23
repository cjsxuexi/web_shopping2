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
 * ��Ʒ����
 */
@WebServlet("/sys/servlet/handler/productHandlerServlet")
public class ProductHandlerServlet extends BaseServlet {

	private static final long serialVersionUID = 7682941860325280907L;

	/**
	 * ��Ʒ���
	 */

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1���Լ�ȥ����һ������
		Product product = new Product();

		// 2���ļ��ϴ�
		try {
			upload(req, product);

			// 3�������ݿ��������������Ʒ
			productService.addProduct(product);

			req.setAttribute("message", "��Ʒ��ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "��Ʒ���ʧ��");
		}
		req.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(req, resp);
	}

	// �ļ��ϴ�
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest req, Product product) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		if (!ServletFileUpload.isMultipartContent(req)) {
			req.setAttribute("message", "�˱�������");
			return;
		}

		// �����������������ʲô����?������ϴ����ļ����ļ�����������
		upload.setHeaderEncoding("UTF-8");

		// ��������
		List<FileItem> fileItems = null;
		try {
			fileItems = upload.parseRequest(req);

			if (fileItems != null && fileItems.size() > 0) {

				// ��ʾ�����ļ��ϴ���·��(Ŀ¼)
				String saveDir = this.getServletContext().getRealPath("/upload");

				for (FileItem item : fileItems) {

					// �ж�item���ļ��ϴ�������ͨ������
					if (item.isFormField()) {
						// ��ʾ������ͨ������
						String filedName = item.getFieldName(); // pname
						String filedValue = item.getString("UTF-8"); // value
						BeanUtils.setProperty(product, filedName, filedValue);
						if ("subProductTypeId".equals(filedName)) {
							ProductType type = new ProductType();
							type.setId(Integer.parseInt(filedValue));
							product.setProductType(type);
						}
					} else {
						// �ļ��ϴ���

						// 1����ȡ�ļ���
						String fileName = item.getName();
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

						// 2������Ψһ���ļ���
						fileName = FileUtils.generateUniqueFileName(fileName);

						// 3��Ŀ¼Ҫ��ɢ
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

						// 4������product��ͼƬ·��
						product.setPimageUrl(FileUtils.getRelativeDir(fileName) + "\\" + fileName);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// --------------------ɾ����Ʒ-----------------

	public String delete(HttpServletRequest request, HttpServletResponse response) {

		try {

			// 1��������Ʒid��ѯ��Ʒ
			String productId = request.getParameter("productId");
			Integer pId = Integer.parseInt(productId);
			Product product = productService.queryProductById(pId);
			// 2���������Ʒ�Ķ�Ӧ��ͼƬҲɾ��
			// 2.1 �ҳ���Ʒ��Ӧ��ͼƬ��·��
			// 2.1.1 �����õ��Ƿ������ϴ�ͼƬ��Ŀ¼
			String imageDirPath = this.getServletContext().getRealPath("/upload");
			// 2.1.2 Ҫ�õ����ͼƬ�ı��������ݿ��е�·��
			String imagePath = product.getPimageUrl();
			File file = new File(imageDirPath, imagePath);
			if (file.exists()) {
				file.delete();
			}

			// 3��������Ʒservice��ȥ������Ʒidɾ����Ʒ(�����ݿ�Ĳ������Ʒ����ɾ��)
			productService.deleteProductById(pId);

			request.setAttribute("message", "ɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ɾ��ʧ��");
		}
		return "f:/WEB-INF/pages/message.jsp";
	}

}
