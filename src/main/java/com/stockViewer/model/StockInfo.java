package com.stockViewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class StockInfo {
    public final List list;

    @JsonCreator
    public StockInfo(@JsonProperty("list") List list){
        this.list = list;
    }

    public static final class List {
        public final Meta meta;
        public final Resource resources[];

        @JsonCreator
        public List(@JsonProperty("meta") Meta meta, @JsonProperty("resources") Resource[] resources){
            this.meta = meta;
            this.resources = resources;
        }

        public static final class Meta {
            public final String type;
            public final long start;
            public final long count;
    
            @JsonCreator
            public Meta(@JsonProperty("type") String type, @JsonProperty("start") long start, 
            		@JsonProperty("count") long count){
                this.type = type;
                this.start = start;
                this.count = count;
            }
        }

        public static final class Resource {
            public final ResourceInner resource;
    
            @JsonCreator
            public Resource(@JsonProperty("resource") ResourceInner resource){
                this.resource = resource;
            }
    
            public static final class ResourceInner {
                public final String classname;
                public final Fields fields;
        
                @JsonCreator
                public ResourceInner(@JsonProperty("classname") String classname, 
                		@JsonProperty("fields") Fields fields){
                    this.classname = classname;
                    this.fields = fields;
                }
        
                public static final class Fields {
                    public final String name;
                    public final String price;
                    public final String symbol;
                    public final String ts;
                    public final String type;
                    public final String utctime;
                    public final String volume;
            
                    @JsonCreator
                    public Fields(@JsonProperty("name") String name, @JsonProperty("price") String price, 
                    		@JsonProperty("symbol") String symbol, @JsonProperty("ts") String ts, 
                    		@JsonProperty("type") String type, @JsonProperty("utctime") String utctime, 
                    		@JsonProperty("volume") String volume){
                        this.name = name;
                        this.price = price;
                        this.symbol = symbol;
                        this.ts = ts;
                        this.type = type;
                        this.utctime = utctime;
                        this.volume = volume;
                    }
                }
            }
        }
    }
}