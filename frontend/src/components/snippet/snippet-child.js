import React from "react";
import ReactMarkdown from "react-markdown";
import rehypeRaw from "rehype-raw";
import remarkGfm from "remark-gfm";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { coldarkDark } from "react-syntax-highlighter/dist/cjs/styles/prism";
import CodeCopyBtn from "./codeCopyBtn";
import "../../assets/main.css";

export default function MarkDowns({ children }) {
  const Pre = ({ children }) => (
    <pre className="blog-pre">
      <CodeCopyBtn>{children}</CodeCopyBtn>
      {children}
    </pre>
  );
  return (
    <ReactMarkdown
      className="post-markdown"
      children={children}
      rehypePlugins={[rehypeRaw]}
      remarkPlugins={[remarkGfm]}
      components={{
        pre: Pre,
        code({ inline, className = "blog-code", children, ...props }) {
          return (
            <SyntaxHighlighter
              children={String(children).replace(/\n$/, "")}
              style={coldarkDark}
              language="javascript"
              PreTag="div"
              showLineNumbers={true}
              wrapLongLines={true}
              {...props}
            />
          );
        },
      }}
    />
  );
}
