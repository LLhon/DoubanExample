package meizu.hon.doubanexample.model;

import java.util.List;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.model
 * @date 2016/5/9 14:47
 * @des
 */
public class MusicModel {


    /**
     * start : 0
     * count : 10
     * total : 30
     * musics : [{"id":10000037,"title":"我只在乎你","alt":"https://music.douban.com/music/10000037","author":[{"name":"邓丽君"}],"alt_title":"留聲經典復刻版","tags":[{"count":20,"name":"经典"},{"count":20,"name":"邓丽君"}],"summary":"邓丽君在1987年推出的唱片专集《我只在乎你》中，有三首歌的词作者是\u201c桃丽莎\u201d。其实，桃丽莎即是邓丽君自己（英文名TERESA的中译）。根据我手中的资料，邓丽君作的词并不多，虽然她确曾向媒体表示\u201c最大的心愿是出一张一脚踢的唱片\u201d\u2014\u2014即由自己包办下全部的词曲和制作，但是因意外去世而没能实现。但是，在此专集中竟有三首之多，不能不令人关注。大体上说，这三首歌具有两种风格，一为写实，一为浪漫。《非龙非彲》以现代汉语与古汉语混合，歌词的意境悲凉，心态哀痛，而且隐含着非比寻常的寓意，笔者愿在此写出来就教于方家。","image":"https://img3.doubanio.com/spic/s11185741.jpg","mobile_link":"https://m.douban.com/music/subject/10000037/","attrs":{"publisher":["环球"],"singer":["邓丽君"],"discs":["1"],"pubdate":["1987-01-02"],"title":["我只在乎你"],"media":["CD"],"tracks":["01. 酒醉的探戈\n02. 像故事般温柔\n03. 命运之川\n04. 爱人\n05. 午夜微风\n06. 夏日圣诞\n07. 非龙非彲\n08. 不着痕迹\n09. 心路过黄昏\n10. 我只在乎你"],"version":["专辑"]},"rating":{"max":10,"average":"0.0","numRaters":20,"min":0}}]
     */

    private int start;
    private int count;
    private int total;
    /**
     * id : 10000037
     * title : 我只在乎你
     * alt : https://music.douban.com/music/10000037
     * author : [{"name":"邓丽君"}]
     * alt_title : 留聲經典復刻版
     * tags : [{"count":20,"name":"经典"},{"count":20,"name":"邓丽君"}]
     * summary : 邓丽君在1987年推出的唱片专集《我只在乎你》中，有三首歌的词作者是“桃丽莎”。其实，桃丽莎即是邓丽君自己（英文名TERESA的中译）。根据我手中的资料，邓丽君作的词并不多，虽然她确曾向媒体表示“最大的心愿是出一张一脚踢的唱片”——即由自己包办下全部的词曲和制作，但是因意外去世而没能实现。但是，在此专集中竟有三首之多，不能不令人关注。大体上说，这三首歌具有两种风格，一为写实，一为浪漫。《非龙非彲》以现代汉语与古汉语混合，歌词的意境悲凉，心态哀痛，而且隐含着非比寻常的寓意，笔者愿在此写出来就教于方家。
     * image : https://img3.doubanio.com/spic/s11185741.jpg
     * mobile_link : https://m.douban.com/music/subject/10000037/
     * attrs : {"publisher":["环球"],"singer":["邓丽君"],"discs":["1"],"pubdate":["1987-01-02"],"title":["我只在乎你"],"media":["CD"],"tracks":["01. 酒醉的探戈\n02. 像故事般温柔\n03. 命运之川\n04. 爱人\n05. 午夜微风\n06. 夏日圣诞\n07. 非龙非彲\n08. 不着痕迹\n09. 心路过黄昏\n10. 我只在乎你"],"version":["专辑"]}
     * rating : {"max":10,"average":"0.0","numRaters":20,"min":0}
     */

    private List<MusicsEntity> musics;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MusicsEntity> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicsEntity> musics) {
        this.musics = musics;
    }

    public static class MusicsEntity {
        private int         id;
        private String      title;
        private String      alt;
        private String      alt_title;
        private String      summary;
        private String      image;
        private String      mobile_link;
        private AttrsEntity attrs;
        /**
         * max : 10
         * average : 0.0
         * numRaters : 20
         * min : 0
         */

        private RatingEntity       rating;
        /**
         * name : 邓丽君
         */

        private List<AuthorEntity> author;
        /**
         * count : 20
         * name : 经典
         */

        private List<TagsEntity>   tags;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMobile_link() {
            return mobile_link;
        }

        public void setMobile_link(String mobile_link) {
            this.mobile_link = mobile_link;
        }

        public AttrsEntity getAttrs() {
            return attrs;
        }

        public void setAttrs(AttrsEntity attrs) {
            this.attrs = attrs;
        }

        public RatingEntity getRating() {
            return rating;
        }

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public List<AuthorEntity> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorEntity> author) {
            this.author = author;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public static class AttrsEntity {
            private List<String> publisher;
            private List<String> singer;
            private List<String> discs;
            private List<String> pubdate;
            private List<String> title;
            private List<String> media;
            private List<String> tracks;
            private List<String> version;

            public List<String> getPublisher() {
                return publisher;
            }

            public void setPublisher(List<String> publisher) {
                this.publisher = publisher;
            }

            public List<String> getSinger() {
                return singer;
            }

            public void setSinger(List<String> singer) {
                this.singer = singer;
            }

            public List<String> getDiscs() {
                return discs;
            }

            public void setDiscs(List<String> discs) {
                this.discs = discs;
            }

            public List<String> getPubdate() {
                return pubdate;
            }

            public void setPubdate(List<String> pubdate) {
                this.pubdate = pubdate;
            }

            public List<String> getTitle() {
                return title;
            }

            public void setTitle(List<String> title) {
                this.title = title;
            }

            public List<String> getMedia() {
                return media;
            }

            public void setMedia(List<String> media) {
                this.media = media;
            }

            public List<String> getTracks() {
                return tracks;
            }

            public void setTracks(List<String> tracks) {
                this.tracks = tracks;
            }

            public List<String> getVersion() {
                return version;
            }

            public void setVersion(List<String> version) {
                this.version = version;
            }
        }

        public static class RatingEntity {
            private int    max;
            private String average;
            private int    numRaters;
            private int    min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class AuthorEntity {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class TagsEntity {
            private int    count;
            private String name;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
