package com.example.testdemo.data;

import java.util.List;

public class ArticleBean {

  private int             curPage;
  private int             offset;
  private boolean         over;
  private int             pageCount;
  private int             size;
  private int             total;
  /**
   * apkLink :
   * audit : 1
   * author : RuffianZhong
   * canEdit : false
   * chapterId : 539
   * chapterName : 未分类
   * collect : false
   * courseId : 13
   * desc : 本项目使用 MVVM 模式架构，使用 Jetpack 组件实现，功能代码实现组件化，目标是编写一个 玩安卓 客户端
   目前 MVVM 框架已经完成，Http 框架已经完成。玩安卓客户端功能代码正在进行中，组件化将在基础功能实现之后完成，请持续关注。。。
   * descMd :
   * envelopePic : https://www.wanandroid.com/resources/image/pc/default_project_img.jpg
   * fresh : false
   * host :
   * id : 17386
   * link : https://www.wanandroid.com/blog/show/2942
   * niceDate : 2021-02-23 23:24
   * niceShareDate : 2021-02-23 23:24
   * origin :
   * prefix :
   * projectLink : https://github.com/RuffianZhong/JetpackMVVM
   * publishTime : 1614093892000
   * realSuperChapterId : 293
   * selfVisible : 0
   * shareDate : 1614093892000
   * shareUser :
   * superChapterId : 294
   * superChapterName : 开源项目主Tab
   * tags : [{"name":"项目","url":"/project/list/1?cid=539"}]
   * title : MVVM+Jetpack+组件化模式架构项目
   * type : 0
   * userId : -1
   * visible : 1
   * zan : 0
   */

  private List<ContentBean> datas;

  public int getCurPage() {
    return curPage;
  }

  public void setCurPage(int curPage) {
    this.curPage = curPage;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public boolean isOver() {
    return over;
  }

  public void setOver(boolean over) {
    this.over = over;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<ContentBean> getDatas() {
    return datas;
  }

  public void setDatas(List<ContentBean> datas) {
    this.datas = datas;
  }

  public static class ContentBean {
    private String  apkLink;
    private int     audit;
    private String  author;
    private boolean canEdit;
    private int     chapterId;
    private String  chapterName;
    private boolean collect;
    private int     courseId;
    private String  desc;
    private String  descMd;
    private String  envelopePic;
    private boolean fresh;
    private String  host;
    private int     id;
    private String  link;
    private String  niceDate;
    private String  niceShareDate;
    private String  origin;
    private String  prefix;
    private String  projectLink;
    private long    publishTime;
    private int     realSuperChapterId;
    private int     selfVisible;
    private long    shareDate;
    private String  shareUser;
    private int     superChapterId;
    private String  superChapterName;
    private String  title;
    private int     type;
    private int     userId;
    private int     visible;
    private int     zan;

    @Override
    public String toString() {
      return "ContentBean{" +
          "apkLink='" + apkLink + '\'' +
          ", audit=" + audit +
          ", author='" + author + '\'' +
          ", canEdit=" + canEdit +
          ", chapterId=" + chapterId +
          ", chapterName='" + chapterName + '\'' +
          ", collect=" + collect +
          ", courseId=" + courseId +
          ", desc='" + desc + '\'' +
          ", descMd='" + descMd + '\'' +
          ", envelopePic='" + envelopePic + '\'' +
          ", fresh=" + fresh +
          ", host='" + host + '\'' +
          ", id=" + id +
          ", link='" + link + '\'' +
          ", niceDate='" + niceDate + '\'' +
          ", niceShareDate='" + niceShareDate + '\'' +
          ", origin='" + origin + '\'' +
          ", prefix='" + prefix + '\'' +
          ", projectLink='" + projectLink + '\'' +
          ", publishTime=" + publishTime +
          ", realSuperChapterId=" + realSuperChapterId +
          ", selfVisible=" + selfVisible +
          ", shareDate=" + shareDate +
          ", shareUser='" + shareUser + '\'' +
          ", superChapterId=" + superChapterId +
          ", superChapterName='" + superChapterName + '\'' +
          ", title='" + title + '\'' +
          ", type=" + type +
          ", userId=" + userId +
          ", visible=" + visible +
          ", zan=" + zan +
          ", tags=" + tags +
          '}';
    }

    /**
     * name : 项目
     * url : /project/list/1?cid=539
     */



    private List<TagsBean> tags;

    public String getApkLink() {
      return apkLink;
    }

    public void setApkLink(String apkLink) {
      this.apkLink = apkLink;
    }

    public int getAudit() {
      return audit;
    }

    public void setAudit(int audit) {
      this.audit = audit;
    }

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public boolean isCanEdit() {
      return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
      this.canEdit = canEdit;
    }

    public int getChapterId() {
      return chapterId;
    }

    public void setChapterId(int chapterId) {
      this.chapterId = chapterId;
    }

    public String getChapterName() {
      return chapterName;
    }

    public void setChapterName(String chapterName) {
      this.chapterName = chapterName;
    }

    public boolean isCollect() {
      return collect;
    }

    public void setCollect(boolean collect) {
      this.collect = collect;
    }

    public int getCourseId() {
      return courseId;
    }

    public void setCourseId(int courseId) {
      this.courseId = courseId;
    }

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }

    public String getDescMd() {
      return descMd;
    }

    public void setDescMd(String descMd) {
      this.descMd = descMd;
    }

    public String getEnvelopePic() {
      return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
      this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
      return fresh;
    }

    public void setFresh(boolean fresh) {
      this.fresh = fresh;
    }

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getLink() {
      return link;
    }

    public void setLink(String link) {
      this.link = link;
    }

    public String getNiceDate() {
      return niceDate;
    }

    public void setNiceDate(String niceDate) {
      this.niceDate = niceDate;
    }

    public String getNiceShareDate() {
      return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
      this.niceShareDate = niceShareDate;
    }

    public String getOrigin() {
      return origin;
    }

    public void setOrigin(String origin) {
      this.origin = origin;
    }

    public String getPrefix() {
      return prefix;
    }

    public void setPrefix(String prefix) {
      this.prefix = prefix;
    }

    public String getProjectLink() {
      return projectLink;
    }

    public void setProjectLink(String projectLink) {
      this.projectLink = projectLink;
    }

    public long getPublishTime() {
      return publishTime;
    }

    public void setPublishTime(long publishTime) {
      this.publishTime = publishTime;
    }

    public int getRealSuperChapterId() {
      return realSuperChapterId;
    }

    public void setRealSuperChapterId(int realSuperChapterId) {
      this.realSuperChapterId = realSuperChapterId;
    }

    public int getSelfVisible() {
      return selfVisible;
    }

    public void setSelfVisible(int selfVisible) {
      this.selfVisible = selfVisible;
    }

    public long getShareDate() {
      return shareDate;
    }

    public void setShareDate(long shareDate) {
      this.shareDate = shareDate;
    }

    public String getShareUser() {
      return shareUser;
    }

    public void setShareUser(String shareUser) {
      this.shareUser = shareUser;
    }

    public int getSuperChapterId() {
      return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
      this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
      return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
      this.superChapterName = superChapterName;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }

    public int getUserId() {
      return userId;
    }

    public void setUserId(int userId) {
      this.userId = userId;
    }

    public int getVisible() {
      return visible;
    }

    public void setVisible(int visible) {
      this.visible = visible;
    }

    public int getZan() {
      return zan;
    }

    public void setZan(int zan) {
      this.zan = zan;
    }

    public List<TagsBean> getTags() {
      return tags;
    }

    public void setTags(List<TagsBean> tags) {
      this.tags = tags;
    }

    public static class TagsBean {
      private String name;
      private String url;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }
    }
  }

  @Override
  public String toString() {
    return "ArticleBean{" +
        "curPage=" + curPage +
        ", offset=" + offset +
        ", over=" + over +
        ", pageCount=" + pageCount +
        ", size=" + size +
        ", total=" + total +
        ", datas=" + datas +
        '}';
  }
}
