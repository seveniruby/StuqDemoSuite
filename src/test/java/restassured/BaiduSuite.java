package restassured;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(Baidu.class)
@Suite.SuiteClasses(Demo.class)
public class BaiduSuite {
}
