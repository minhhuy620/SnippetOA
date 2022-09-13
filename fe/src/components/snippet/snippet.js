import { useState } from "react";
import MarkDowns from "./snippet-child";
import "../../assets/main.css";

export default function PostBody() {
  // hard code
  // const initialCodeString = [
  //   {
  //     id: 1,
  //     code: `function createStyleObject(classNames, style) {
  //             return classNames.reduce((styleObject, className) => {
  //               return {...styleObject, ...style[className]};
  //             }, {});
  //           }
  //         }`,
  //   },
  //   {
  //     id: 2,
  //     code: `function createStyleObject(classNames, style) {
  //             return classNames.reduce((styleObject, className) => {
  //             return {...styleObject, ...style[className]};
  //             }, {});
  //           }
  //         }`,
  //   },
  // ];
  const code = [
    {
      // basic_syntax: [
      // {
      element: "Code",
      syntax:
        "```\nfunction createStyleObject(classNames,style){ \n\treturn classNames.reduce((styleObject, className) => { \n\t\treturn {...styleObject, ...style[className]};\n}}\n```",
      // },
      // ],
    },
    {
      // extended_syntax: [
      // {
      element: "Fenced Code Blocks",
      syntax:
        '```\n{\n\t"firstName": "John",\n\t"lastName": "Smith",\n\t"age": 25\n}\n```\n',
      // },
      // ],
    },
    {
      // basic_syntax: [
      // {
      element: "Code",
      syntax:
        "```\nfunction createChildren(style, useInlineStyles) { \n\treturn classNames.reduce((styleObject, className) => { \n\t\treturn {...styleObject, ...style[className]};\n}}\n```",
      // },
      // ],
    },
    {
      // basic_syntax: [
      // {
      element: "Code",
      syntax:
        "`function createChildren(style, useInlineStyles) { let childrenCount = 0; return children => {childrenCount += 1; return children.map((child, i) => createElement({ node: child, style, useInlineStyles,key:code-segment-${childrenCount}-${i}}));}}`",
      // },
      // ],
    },
  ];
  // const url = "https://www.markdownguide.org/api/v1/cheat-sheet.json";
  const [markdown, setMarkdown] = useState(code);
  // useEffect(() => {
  // fetch(url)
  //   .then((res) => res.json())
  //   .then(({ cheat_sheet }) => {
  //     console.log(cheat_sheet);
  // setMarkdownCheatSheets([
  //   ...cheat_sheet[1]?.extended_syntax,
  //   ...cheat_sheet[0]?.basic_syntax,
  // ]);
  // })
  // .catch((err) => {
  //   console.log(err);
  // });
  // }, []);

  return (
    <>
      {markdown && (
        <>
          {markdown.map(({ element, syntax }) => {
            return (
              <div key={element}>
                <MarkDowns children={syntax} />
              </div>
            );
          })}
        </>
      )}
    </>
  );
}
