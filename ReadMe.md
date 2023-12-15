# ITextUtils 的使用方法

## 1. 方法解释

- setBaseFont 与 getBaseFont ,默认使用的BaseFont 的内容，如果不使用的话，会添加
  assets/fonts/brandon_medium.otf 字体作为默认字体
- getDeleteFile 删除图片的方法
- addTitile 添加标题，靠更改 fontSize 确定几级标题
- addNormalTable 添加默认的表格
- addNullLine 添加空行
- addFileAttributes 给pdf文件添加属性
- addContent 添加内容
- todo : getAllClose 关闭iText 文档，最后一定要进行使用
- addSpecialVerticalContentTable 实现特殊表格的方式1 （已经废除）
    - skipeCell 跳过cell
    - shouldMergeCell 判断是否需要合并单元格
    - getMergedCellCoordinates 获取合并单元格的起始和结束坐标
- 現在需要使用的添加特殊表格的方式
    - clearCellLists 清除所有的cell 列表
    - initCellLists 初始化cell 列表
    - addCellLists 添加行
    - mergeCellLists 合并表格
    - setCellListsText 设置表格的内容 todo 后续改进，现在没有内容
    - saveCellListsToDoc 保存表格到文档中
    - setCellTextColor 设置表格中内容的颜色
      -- 添加特殊表格的使用方式为 initCellLists -> addCellLists/mergeCellLists -> setCellListsText->
      setCellTextColor (可省略) -> saveCellListsToDoc
- test 一个测试的方法，里面记录了一些测试的内容，可以先尝试一下
- addSdCardFileImg 添加 sd卡中的文件到 pdf 中
- addResuceFileImg 添加 drawab 中的图片到sd卡中
- addLine 添加实线
- addDottedLine 添加点线
- addUnderLineContent 添加下划线
- addContentUse 中文的测试内容

## 已经解决的问题：

- 加载 drawab 会感觉很慢 ,问题的原因是换页导致的问题，暂时无法解决 已解决
- 暂时无法添加中文 已解决，将 encoding 由 utf-8 修改为 IDENTITY_H 已解决，不过使用的时候需要注意字库是否可以应用于商用
- 输入的时候插入的时候是 8*3 ，但是使用的时候是从坐标轴 0,0 开始的和实际使用不相符，需要修改 未完成
- 合并单元格的时候没有添加判断 已解决 todo 可以根据实际进行修改
- 合并的单元格中设置字体颜色会导致合并的单元格再次分开 已解决 