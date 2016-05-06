package meizu.hon.doubanexample.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.model
 * @Date 2016/5/5 15:07
 * @Des
 */
public class MovieModel implements Parcelable {


    /**
     * count : 1
     * start : 0
     * total : 250
     * subjects : [{"rating":{"max":10,"average":9.6,"stars":"50","min":0},"genres":["犯罪","剧情"],"title":"肖申克的救赎","casts":[{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"}],"collect_count":922646,"original_title":"The Shawshank Redemption","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}],"year":"1994","images":{"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.jpg"},"alt":"https://movie.douban.com/subject/1292052/","id":"1292052"}]
     * title : 豆瓣电影Top250
     */

    private int count;
    private int    start;
    private int    total;
    private String title;
    /**
     * rating : {"max":10,"average":9.6,"stars":"50","min":0}
     * genres : ["犯罪","剧情"]
     * title : 肖申克的救赎
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"}]
     * collect_count : 922646
     * original_title : The Shawshank Redemption
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}]
     * year : 1994
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.jpg"}
     * alt : https://movie.douban.com/subject/1292052/
     * id : 1292052
     */

    private List<SubjectsEntity> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsEntity> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsEntity implements Parcelable {
        /**
         * max : 10
         * average : 9.6
         * stars : 50
         * min : 0
         */

        private RatingEntity rating;
        private String title;
        private int    collect_count;
        private String original_title;
        private String subtype;
        private String year;
        /**
         * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg
         * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.jpg
         * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.jpg
         */

        private ImagesEntity images;
        private String       alt;
        private String       id;
        private List<String> genres;
        /**
         * alt : https://movie.douban.com/celebrity/1054521/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"}
         * name : 蒂姆·罗宾斯
         * id : 1054521
         */

        private List<CastsEntity>     casts;
        /**
         * alt : https://movie.douban.com/celebrity/1047973/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"}
         * name : 弗兰克·德拉邦特
         * id : 1047973
         */

        private List<DirectorsEntity> directors;

        public RatingEntity getRating() {
            return rating;
        }

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesEntity getImages() {
            return images;
        }

        public void setImages(ImagesEntity images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsEntity> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsEntity> casts) {
            this.casts = casts;
        }

        public List<DirectorsEntity> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsEntity> directors) {
            this.directors = directors;
        }

        public static class RatingEntity implements Parcelable {
            private int    max;
            private double average;
            private String stars;
            private int    min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            @Override public int describeContents() {
                return 0;
            }

            @Override public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.max);
                dest.writeDouble(this.average);
                dest.writeString(this.stars);
                dest.writeInt(this.min);
            }

            public RatingEntity() {
            }

            protected RatingEntity(Parcel in) {
                this.max = in.readInt();
                this.average = in.readDouble();
                this.stars = in.readString();
                this.min = in.readInt();
            }

            public static final Creator<RatingEntity> CREATOR = new Creator<RatingEntity>() {
                @Override public RatingEntity createFromParcel(Parcel source) {
                    return new RatingEntity(source);
                }

                @Override public RatingEntity[] newArray(int size) {
                    return new RatingEntity[size];
                }
            };
        }

        public static class ImagesEntity implements Parcelable {
            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            @Override public int describeContents() {
                return 0;
            }

            @Override public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.small);
                dest.writeString(this.large);
                dest.writeString(this.medium);
            }

            public ImagesEntity() {
            }

            protected ImagesEntity(Parcel in) {
                this.small = in.readString();
                this.large = in.readString();
                this.medium = in.readString();
            }

            public static final Creator<ImagesEntity> CREATOR = new Creator<ImagesEntity>() {
                @Override public ImagesEntity createFromParcel(Parcel source) {
                    return new ImagesEntity(source);
                }

                @Override public ImagesEntity[] newArray(int size) {
                    return new ImagesEntity[size];
                }
            };
        }

        public static class CastsEntity implements Parcelable {
            private String alt;
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/17525.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/17525.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/17525.jpg
             */

            private AvatarsEntity avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsEntity getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsEntity avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsEntity implements Parcelable {
                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                @Override public int describeContents() {
                    return 0;
                }

                @Override public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.small);
                    dest.writeString(this.large);
                    dest.writeString(this.medium);
                }

                public AvatarsEntity() {
                }

                protected AvatarsEntity(Parcel in) {
                    this.small = in.readString();
                    this.large = in.readString();
                    this.medium = in.readString();
                }

                public static final Creator<AvatarsEntity> CREATOR = new Creator<AvatarsEntity>() {
                    @Override public AvatarsEntity createFromParcel(Parcel source) {
                        return new AvatarsEntity(source);
                    }

                    @Override public AvatarsEntity[] newArray(int size) {
                        return new AvatarsEntity[size];
                    }
                };
            }

            @Override public int describeContents() {
                return 0;
            }

            @Override public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.alt);
                dest.writeParcelable(this.avatars, flags);
                dest.writeString(this.name);
                dest.writeString(this.id);
            }

            public CastsEntity() {
            }

            protected CastsEntity(Parcel in) {
                this.alt = in.readString();
                this.avatars = in.readParcelable(AvatarsEntity.class.getClassLoader());
                this.name = in.readString();
                this.id = in.readString();
            }

            public static final Creator<CastsEntity> CREATOR = new Creator<CastsEntity>() {
                @Override public CastsEntity createFromParcel(Parcel source) {
                    return new CastsEntity(source);
                }

                @Override public CastsEntity[] newArray(int size) {
                    return new CastsEntity[size];
                }
            };
        }

        public static class DirectorsEntity implements Parcelable {
            private String alt;
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/230.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/230.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/230.jpg
             */

            private AvatarsEntity avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsEntity getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsEntity avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsEntity implements Parcelable {
                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                @Override public int describeContents() {
                    return 0;
                }

                @Override public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.small);
                    dest.writeString(this.large);
                    dest.writeString(this.medium);
                }

                public AvatarsEntity() {
                }

                protected AvatarsEntity(Parcel in) {
                    this.small = in.readString();
                    this.large = in.readString();
                    this.medium = in.readString();
                }

                public static final Creator<AvatarsEntity> CREATOR = new Creator<AvatarsEntity>() {
                    @Override public AvatarsEntity createFromParcel(Parcel source) {
                        return new AvatarsEntity(source);
                    }

                    @Override public AvatarsEntity[] newArray(int size) {
                        return new AvatarsEntity[size];
                    }
                };
            }

            @Override public int describeContents() {
                return 0;
            }

            @Override public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.alt);
                dest.writeParcelable(this.avatars, flags);
                dest.writeString(this.name);
                dest.writeString(this.id);
            }

            public DirectorsEntity() {
            }

            protected DirectorsEntity(Parcel in) {
                this.alt = in.readString();
                this.avatars = in.readParcelable(AvatarsEntity.class.getClassLoader());
                this.name = in.readString();
                this.id = in.readString();
            }

            public static final Creator<DirectorsEntity> CREATOR = new Creator<DirectorsEntity>() {
                @Override public DirectorsEntity createFromParcel(Parcel source) {
                    return new DirectorsEntity(source);
                }

                @Override public DirectorsEntity[] newArray(int size) {
                    return new DirectorsEntity[size];
                }
            };
        }

        @Override public int describeContents() {
            return 0;
        }

        @Override public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.rating, flags);
            dest.writeString(this.title);
            dest.writeInt(this.collect_count);
            dest.writeString(this.original_title);
            dest.writeString(this.subtype);
            dest.writeString(this.year);
            dest.writeParcelable(this.images, flags);
            dest.writeString(this.alt);
            dest.writeString(this.id);
            dest.writeStringList(this.genres);
            dest.writeList(this.casts);
            dest.writeList(this.directors);
        }

        public SubjectsEntity() {
        }

        protected SubjectsEntity(Parcel in) {
            this.rating = in.readParcelable(RatingEntity.class.getClassLoader());
            this.title = in.readString();
            this.collect_count = in.readInt();
            this.original_title = in.readString();
            this.subtype = in.readString();
            this.year = in.readString();
            this.images = in.readParcelable(ImagesEntity.class.getClassLoader());
            this.alt = in.readString();
            this.id = in.readString();
            this.genres = in.createStringArrayList();
            this.casts = new ArrayList<CastsEntity>();
            in.readList(this.casts, CastsEntity.class.getClassLoader());
            this.directors = new ArrayList<DirectorsEntity>();
            in.readList(this.directors, DirectorsEntity.class.getClassLoader());
        }

        public static final Creator<SubjectsEntity> CREATOR = new Creator<SubjectsEntity>() {
            @Override public SubjectsEntity createFromParcel(Parcel source) {
                return new SubjectsEntity(source);
            }

            @Override public SubjectsEntity[] newArray(int size) {
                return new SubjectsEntity[size];
            }
        };
    }

    @Override public String toString() {
        return "MovieModel{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeInt(this.start);
        dest.writeInt(this.total);
        dest.writeString(this.title);
        dest.writeList(this.subjects);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.count = in.readInt();
        this.start = in.readInt();
        this.total = in.readInt();
        this.title = in.readString();
        this.subjects = new ArrayList<SubjectsEntity>();
        in.readList(this.subjects, SubjectsEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
