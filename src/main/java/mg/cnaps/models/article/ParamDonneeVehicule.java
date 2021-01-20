package mg.cnaps.models.article;

public class ParamDonneeVehicule {
	private ArticleMod article;
	private CodeArticleMod [] codeArticle;
	
	public ArticleMod getArticle() {
		return article;
	}
	public void setArticle(ArticleMod article) {
		this.article = article;
	}
	public CodeArticleMod[] getCodeArticle() {
		return codeArticle;
	}
	public void setCodeArticle(CodeArticleMod[] codeArticle) {
		this.codeArticle = codeArticle;
	} 
}
