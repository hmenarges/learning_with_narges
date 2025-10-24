<div dir="rtl" style="font-family: Vazirmatn">
    <header>
        <h1>
            خلاصه دوره: مبانی اسپرینگ (Spring Fundamentals)
        </h1>
        <a href="https://pluralsight.com/library/courses/spring-framework-spring-fundamentals/table-of-contents">
            بر اساس دوره آموزشی ارائه شده توسط Pluralsight - Bryan Hansen
        </a>
    </header>
    <section>
        <h2>
            اسپرینگ چیست؟
        </h2>
        <p>
            فریم‌ورک اسپرینگ (Spring) اساساً یک
            <strong>محفظه وارونگی کنترل (Inversion of Control - IoC)</strong>
            است. این تکنیک که اغلب به عنوان
            <strong>تزریق وابستگی (Dependency Injection - DI)</strong>
            شناخته می‌شود، برای ساده‌سازی و کاهش پیچیدگی‌های پیکربندی در توسعه برنامه‌های جاوا (به ویژه جایگزینی برای EJB های پیچیده قدیمی) طراحی شده است.
        </p>
        <p>
            اسپرینگ بر توسعه مبتنی بر
            <strong>POJO</strong>
            (Plain Old Java Object) تمرکز دارد و به توسعه‌دهندگان کمک می‌کند تا کدی تمیزتر، با قابلیت تست‌پذیری، نگهداری و مقیاس‌پذیری بالاتر بنویسند و بیشتر بر منطق تجاری (Business Logic) تمرکز کنند.
        </p>
    </section>
    <section>
        <h2>
            روش‌های اصلی پیکربندی در اسپرینگ
        </h2>
        <p>
            این دوره سه روش اصلی برای پیکربندی و سیم‌کشی (wiring) کامپوننت‌ها (Bean ها) در اسپرینگ را پوشش می‌دهد:
        </p>
        <ul>
            <li>
                <strong>پیکربندی مبتنی بر جاوا (Java Configuration):</strong>
                روشی مدرن و ترجیح داده شده که در آن از فایل‌های جاوا و انوتیشن‌هایی (Annotation) مانند
                <code dir="ltr">@Configuration</code>
                و
                <code dir="ltr">@Bean</code>
                برای تعریف وابستگی‌ها استفاده می‌شود.
            </li>
            <li>
                <strong>پیکربندی مبتنی بر XML:</strong>
                روش سنتی و قدیمی‌تر که در آن Bean ها و وابستگی‌های آن‌ها در یک فایل
                <code>applicationContext.xml</code>
                تعریف می‌شوند.
            </li>
            <li>
                <strong>سیم‌کشی خودکار (Autowiring):</strong>
                استفاده از انوتیشن‌هایی مانند
                <code dir="ltr">@Autowired</code>
                برای تزریق خودکار وابستگی‌ها. این روش اغلب با انوتیشن‌های استریوتایپ (Stereotype) مانند
                <code dir="ltr">@Component</code>،
                <code dir="ltr">@Service</code>
                و
                <code dir="ltr">@Repository</code>
                همراه است.
            </li>
        </ul>
    </section>
    <section>
        <h2>
            مفاهیم کلیدی پوشش داده شده
        </h2>
        <h3>تزریق وابستگی (DI)</h3>
        <ul>
            <li>
                <strong>تزریق از طریق Setter:</strong>
                تزریق وابستگی‌ها با فراخوانی متدهای Setter.
            </li>
            <li>
                <strong>تزریق از طریق Constructor:</strong>
                تزریق وابستگی‌ها از طریق سازنده کلاس.
            </li>
        </ul>
        <h3 dir="rtl">(Bean Scopes) Bean دامنه‌های </h3>
        <div dir="rtl">
            <ul>
                <li>
                    <strong>(پیش‌فرض) Singleton: </strong>
                        تنها یک نمونه از Bean در spring container ایحاد می‌شود.
                <li>
                    <strong>Prototype:</strong>
                        به ازای هر درخواست، یک نمونه جدید از Bean ایجاد می‌شود.
                </li>
                <li>
                    <strong>Web Scopes:</strong>  
                    در برنامه‌های وب کاربرد دارند.
                        <ul dir="rtl">
                            <li>
                                Request
                            </li>
                            <li>
                                Session
                            </li>        
                            <li>
                                Global
                            </li>
                        </ul>
                </li>
            </ul>
        </div>
        <h3>پیکربندی پیشرفته Bean</h3>
        <ul>
            <li>
                <strong>چرخه حیات Bean:</strong>
                استفاده از
                <code dir="ltr">@PostConstruct</code>
                برای اجرای متدها پس از ساخته شدن Bean.
            </li>
            <li>
                <strong>FactoryBean:</strong>
                روشی برای کپسوله کردن منطق پیچیده ساخت Bean (مثلاً برای ادغام با کدهای قدیمی یا استاتیک).
            </li>
            <li>
                <strong>زبان عبارتی اسپرینگ (SpEL):</strong>
                برای ارزیابی و دستکاری مقادیر در زمان اجرا.
            </li>
            <li>
                <strong>پروفایل‌های Bean (Bean Profiles):</strong>
                استفاده از
                <code dir="ltr">@Profile</code>
                برای فعال‌سازی پیکربندی‌های مختلف بر اساس محیط (مانند توسعه، تست یا تولید).
            </li>
        </ul>    
    </section>
</div>
