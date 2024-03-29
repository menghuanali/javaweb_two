###编程任务
用后端技术按照设计原型制作一个服务器管理网站的服务管理功能。
####要求：
1 整体页面布局如下：
![](https://upload-images.jianshu.io/upload_images/12058546-bff904aafb82ed65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2 设计并实现一个数据库，用于管理多个服务和多个服务器之间的关系。一个服务可以被部署到多个服务器上，同时，一个服务器可以部署多个服务，且在一个服务器上同一个服务只能部署一次。
3 页面左边列出了所有可选的服务：
- 3.1 可以通过在文本框输入新服务的名字来添加新服务，服务名称不能已经存在，若存在将提示添加失败。
- 3.2 可以通过某服务右边的删除链接来删除该服务，若该服务在某个服务器上已经部署，则删除链接将不显
示。

4 右边列出了服务器中服务的部署情况。为简化考试，后端逻辑假设仅考虑两个服务器（但数据库设计必须体现出能支持多个服务器）。
- 4.1.1 用户可以勾选任意服务器，勾选后，服务器名称背景高亮显示，
- 4.1.2 服务器可以多选。
- 4.1.3 原始服务本身已经固化在服务器中，无法选中。

5 中间栏有“添加”和“卸载”两个按钮：
- 5.1 添加按钮把左边栏中选中的服务添加到右边栏中选中的服务器当中。若左边栏未选中服务，则弹出模式对话框提示错误。同样，若右边栏未选中服务器，也使用模式对话框提示相应错误。若向同一非服务器重复添加同一服务时，也提示相应错误。
- 5.2 在将左边服务添加到右边时，出界面显示添加状况之外，数据库必须同时提现服务的从属关系。
- 5.3 卸载按钮把右边栏中选中的服务从对应服务器中卸载，同时数据库必须有所体现。
- 5.4 重新打开页面之后，必须能从数据库中加载上次选择的服务从属关系。
![](https://upload-images.jianshu.io/upload_images/12058546-ba58471f82f79920.gif?imageMogr2/auto-orient/strip)

![整个项目构成图](https://upload-images.jianshu.io/upload_images/12058546-9f8380a63a65c025.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
