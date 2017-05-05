package rml.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import rml.model.*;
import rml.request.BaseUserBook;
import rml.request.UserComment;

import java.util.List;

@Service("bookBaseDao")
public interface BookBaseDao {
	//test
	public List<BaseBook> queryBookBase();
	
	public int insertBookBase(BaseBook baseBook);
	
	//总条数
	public Integer queryBookBasePage(QueryVo vo);
	//结果集
	public List<BookDetails> queryBookBaseListByQueryVo(QueryVo vo);
	//通过Id找到书目
	public List<BaseBook> queryBookById(Integer id);
	//修改书目通过Id
	public void updateBookById(BaseBook basebook);
	//通过Id 删除客户
	public void deleteBookById(Integer id);
	
	//通过扫面isbn 添加图书信息
	public int insertBookDetails(BookDetails bookdetails);
	//插入作者表数据
	public int insertBookAuthor(BookAuthor bookAuthor);
	//插入图书封面表
	public int insertBookImages(@Param("small") String small,
								@Param("large") String large,
								@Param("medium") String medium,
								@Param("id") String id);
	//插入图书标签tag.getCount(),tag.getName(),tag.getTitle()
	public int insertBookTags( @Param("id")String id,
							  @Param("count") Integer count,
							  @Param("name") String name,
							  @Param("title") String title
							 );
	//添豆瓣评分数据
	public int insertBookRating( @Param("id") String id,@Param("average") String average);
	// 查询查入的数据
	public BookDetails selectBookDetails(String id);
	public String[] selectBookAuthor(String id);
	public BookImages  selectBookImages(String id);
	public List<BookTags>   selectBookTag(String id);
	public BookRating selectBookRating(String id);
	
	//改变书的借约状态
	public int changeBookState(UserBookState userBookState);
	public int changeBookStateToBase(UserBookState userBookState);
	//收藏，取消收藏
	public int changeBookFavourite(UserBookFavourite userBookFavourite);
	public int changeBookFavouriteToBase(UserBookFavourite userBookFavourite);
	//收藏(添加收藏)
	public int insertFavouriteList(UserBookFavourite userBookFavourite);
	//查询收藏列表
	public int selectFavouriteList(UserBookFavourite userBookFavourite);
	//删除收藏列表(取消收藏)
	public int deleteFavouriteList(UserBookFavourite userBookFavourite);

	//查询用户和书的关系表
	public BaseUserBook selectUserBookNexusByIdUid(BaseUserBook baseUserBook);
	//修改用户和书的关系表
	public int updateUserBook(BaseUserBook baseUserBook);
	//添加一条关系数据
	public int insertUserBook(BaseUserBook baseUserBook); 
	
	//根据用户id nexusState 查询表
	public int selectUserBookCount(BaseUserBook baseUserBook);
	
	//根据关系状态查询 书的id
	//public List<String> selectUserBookList(BaseUserBook baseUserBook);
	public List<selectUserBookTime> selectUserBookList(BaseUserBook baseUserBook);


	//写书评
	public int insertUserComment(UserComment userComment);
	//用户书评数量
	public int selectUserCommentCount(String uid);
	//用户书评列表
	public List<UserComment> selectUserCommentList(String uid);
	public Integer selectuserBookCountByUid(QueryUserComment vo);

	//书的评论列表
	public List<UserComment> selectUserCommentBookList(String id);
	//书的评论列表 分页
	public Integer selectuserBookCountByid(QueryBookComment vo);
	public List<UserComment> selectUserBookListById(QueryBookComment vo);

	//查询评论用户
	BookCommentUserItem selectUserWeappByBookId(String  uid);

	//根据书的id 查询状态
	List<BaseUserBook> selectuserBookNexus( String id);


	/***********************************************************************
	 *  搜索功能用到的
	 */

	//通过isbn模糊查询书
	List<SearchResultBean> searchBookByISBN_dim(String isbnID);


	//通过书名查询书
	List<SearchResultBean> searchBookByBookName_dim(String bookName);

	//通过作者查询书
	List<String> searchBookIDbyAuthor_dim(String authorName);

	//通过书的ID查询图书详情
	SearchResultBean searchBookByID(String id);

	//通过书的ID查询作者
	String[] searchAuthorByID(String ID);

	//通过书的ID查询图片
	BookImages searchImageByID(String ID);

	/***************************************************************************/

}
