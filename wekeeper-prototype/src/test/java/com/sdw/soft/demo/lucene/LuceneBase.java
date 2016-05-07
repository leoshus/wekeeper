package com.sdw.soft.demo.lucene;

import java.io.File;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author shangyd
 * @date 2015年11月13日 上午11:13:31
 **/
public class LuceneBase {

	private static Directory directory;
	
	/**
	 * 构建IndexWriter
	 * @author shangyd
	 * @date 2015年11月13日 上午11:21:43
	 * 
	 * @return
	 * @throws Exception
	 */
	public IndexWriter getWriter()throws Exception{
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);//设置标准分词器 默认是一元分词
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
		return new IndexWriter(directory, config);
	}
	
	/**
	 * 添加
	 * @author shangyd
	 * @date 2015年11月13日 下午1:56:23
	 * 
	 * @param indexPath
	 */
	public void add(String indexPath){
		IndexWriter indexWriter = null;
		try {
			directory = FSDirectory.open(new File(indexPath));//设置索引文件路径
			indexWriter = getWriter();
			Document doc = new Document();
			doc.add(new StringField("id","1",Store.YES));
			doc.add(new StringField("name", "lucene学习", Store.YES));
			doc.add(new StringField("content", "lucene 垂直化搜索引擎架构", Store.YES));
			indexWriter.addDocument(doc);
			indexWriter.forceMerge(1);//优化压缩段 数据量大不建议使用 会影响性能 
			indexWriter.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(indexWriter != null){
				try {
					indexWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	/**
	 * 搜索
	 * @author shangyd
	 * @date 2015年11月13日 下午2:01:53
	 * 
	 * @param indexPath
	 * @param field
	 * @param searchText
	 */
	public void searchTermQuery(String indexPath,String field,String searchText){
		try {
			directory = FSDirectory.open(new File(indexPath));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);//搜索
			Query query = new TermQuery(new Term(field,searchText));
			TopDocs td = searcher.search(query, 1000);//获取最高得分命中
			for(ScoreDoc sd : td.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				System.out.println("id:" + doc.get("id"));
				System.out.println("name:" + doc.get("name"));
				System.out.println("content:" + doc.get("content"));
			}
			reader.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @author shangyd
	 * @date 2015年11月13日 下午2:14:21
	 * 
	 * @param indexPath
	 * @param field
	 * @param searchText
	 */
	public void simpleSearch(String indexPath,String field,String searchText){
		try {
			directory = FSDirectory.open(new File(indexPath));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			QueryParser parser = new QueryParser(Version.LUCENE_43, field, new StandardAnalyzer(Version.LUCENE_43));
			Query query = parser.parse(searchText);
			TopDocs td = searcher.search(query, 10000);
			System.out.println("命中数据：" + td.scoreDocs.length);
			for(ScoreDoc sd : td.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				System.out.println("id=" + doc.get("id"));
				System.out.println("name =" + doc.get("name"));
				System.out.println("content = "+ doc.get("content"));
			}
			reader.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询索引总数
	 * @author shangyd
	 * @date 2015年11月13日 下午2:17:08
	 * 
	 * @param indexPath
	 * @return
	 */
	public int findIndexTotalCount(String indexPath){
		int count = 0;
		try {
			directory = FSDirectory.open(new File(indexPath));
			IndexReader reader = DirectoryReader.open(directory);
			count = reader.numDocs();
			reader.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 删除
	 * @author shangyd
	 * @date 2015年11月13日 下午2:17:48
	 * 
	 * @param indexPath
	 * @param id
	 */
	public void delete(String indexPath,String id){
		try {
			directory = FSDirectory.open(new File(indexPath));
			IndexWriter writer = getWriter();
			Query query = new TermQuery(new Term("id",id));
			writer.deleteDocuments(query);
			writer.commit();
			writer.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过ID更新内容
	 * @author shangyd
	 * @date 2015年11月13日 下午2:22:00
	 * 
	 * @param indexPath
	 * @param docId
	 * @param map
	 */
	public void updateContentById(String indexPath,String docId,Map<String,String> map){
		try {
			directory = FSDirectory.open(new File(indexPath));
			IndexWriter writer = getWriter();
			Document doc = new Document();
			doc.add(new StringField("id",map.get("id").toString(),Store.YES));
			doc.add(new StringField("name",map.get("name").toString(),Store.YES));
			doc.add(new StringField("content",map.get("content").toString(),Store.YES));
			writer.updateDocument(new Term("id",docId),doc);
			writer.commit();
			writer.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
